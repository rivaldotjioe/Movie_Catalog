package com.rivaldo.moviecatalog.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var title: String,
    var desc: String,
    var image: Int
) : Parcelable
