package com.rivaldo.moviecatalog.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Movie(
    var title: String,
    var desc: String,
    var image: Int
) : Parcelable
