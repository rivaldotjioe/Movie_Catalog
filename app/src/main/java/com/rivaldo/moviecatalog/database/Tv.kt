package com.rivaldo.moviecatalog.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favoritetv")
data class Tv(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvid")
    var id: Int,
    @ColumnInfo(name = "titletv")
    var name: String,
    @ColumnInfo(name = "desctv")
    var desc: String,
    @ColumnInfo(name = "image_tv")
    var image: String
) : Parcelable
