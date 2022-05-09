package com.tenutz.cracknotifier.util

import com.google.gson.Gson
import com.tenutz.cracknotifier.data.api.dto.common.ErrorResponse
import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.lang.Exception

fun ResponseBody.toErrorResponseOrNull(): ErrorResponse? {
    return try {
        Gson().fromJson(charStream(), ErrorResponse::class.java).run { if(isNotEmpty()) this else null }
    } catch (e: Exception) {
        null
    }
}

fun ResponseBody.toTokenResponseOrNull(): TokenResponse? {
    return try {
        Gson().fromJson(charStream(), TokenResponse::class.java).run { if(isNotEmpty()) this else null }
    } catch (e: Exception) {
        null
    }
}

fun Throwable.toErrorResponseOrNull(): ErrorResponse? {
    return try {
        if(this is HttpException) {
            this.response()?.errorBody()?.toErrorResponseOrNull()
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}