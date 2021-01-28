package com.rivaldo.moviecatalog.MovieList

import com.rivaldo.moviecatalog.Data.Movie
import com.rivaldo.moviecatalog.R
import java.util.ArrayList

object MovieData {

    private val titleMovie = arrayOf("A Start Is Born", "Alita")
    private val movieImage = intArrayOf(R.drawable.poster_a_start_is_born, R.drawable.poster_alita, R.drawable.poster_aquaman)
    private val descMovie =  arrayOf("Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.",
        "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. lalu.")

   public fun generateMovieData(): ArrayList<Movie> {
        val movies = ArrayList<Movie>()
        if (titleMovie != null) {
            for (i in titleMovie.indices) {
                movies.add(Movie(titleMovie[i], descMovie[i], movieImage[i]))
            }
        }
        return movies
    }
}