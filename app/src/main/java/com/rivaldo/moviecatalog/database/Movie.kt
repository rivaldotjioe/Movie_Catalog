package com.rivaldo.moviecatalog.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favoritemovie")
data class Movie(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "movieId")
        var id: Int,

        @ColumnInfo(name = "title_movie")
        var title: String,
        @ColumnInfo(name = "description")
        var desc: String,
        @ColumnInfo(name = "image_movie")
        var image: String
) : Parcelable
