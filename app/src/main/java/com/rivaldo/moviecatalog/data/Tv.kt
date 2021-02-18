package com.rivaldo.moviecatalog.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tv(
    var name: String,
    var desc: String,
    var image: Int
) : Parcelable
