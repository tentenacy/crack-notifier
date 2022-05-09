package com.tenutz.cracknotifier.data.paging.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Cracks(
    val total: Int = 0,
    val page: Int = 0,
    val cracks: List<Crack>
): Parcelable {

    @IgnoredOnParcel
    val endOfPage = total - 1 <= page

    @Parcelize
    @Entity(tableName = "cracks")
    data class Crack(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val seq: String,
        val imageUrl: String? = null,
        val createdAt: Date? = null,
        val accuracy: Float,
        val region3DepthName: String? = null,
    ): Parcelable

    @Parcelize
    @Entity(tableName = "crack_remote_keys")
    data class CrackRemoteKeys(
        @PrimaryKey val crackSeq: String,
        val prevKey: Int?,
        val nextKey: Int,
    ): Parcelable
}