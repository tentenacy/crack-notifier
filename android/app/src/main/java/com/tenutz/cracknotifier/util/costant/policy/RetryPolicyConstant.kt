package com.tenutz.cracknotifier.util.costant.policy

import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.common.ErrorCode
import com.tenutz.cracknotifier.util.toErrorResponseOrNull
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


typealias RETRY_PREDICATE = (Throwable) -> Boolean

object RetryPolicyConstant {

    const val MAX_RETRIES = 3L
    const val DEFAULT_INTERVAL = 1L

    val TIMEOUT: RETRY_PREDICATE = { it is SocketTimeoutException }
    val NETWORK: RETRY_PREDICATE = { it is IOException }
    val SERVICE_UNAVAILABLE: RETRY_PREDICATE = { it is HttpException && it.code() == 503 }
    val ACCESS_TOKEN_EXPIRED: RETRY_PREDICATE = {
        it is HttpException && it.response()?.errorBody()?.toErrorResponseOrNull()
            ?.run {
                Logger.e("code: $code")
                code == ErrorCode.ACCESS_TOKEN_ERROR.code
            } ?: false
    }
}