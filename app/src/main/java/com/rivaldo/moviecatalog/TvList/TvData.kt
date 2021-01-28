package com.rivaldo.moviecatalog.TvList

import com.rivaldo.moviecatalog.Data.Tv
import com.rivaldo.moviecatalog.R
import java.util.ArrayList

object TvData {

    private val titleTv = arrayOf("Arrow", "Doom Patrol")
    private val tvImage = intArrayOf(R.drawable.tv_poster_arrow, R.drawable.tv_poster_doom_patrol, R.drawable.tv_poster_dragon_ball)
    private val descTv =  arrayOf("Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
        "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.")

    public fun generateTvData(): ArrayList<Tv> {
        val tv = ArrayList<Tv>()
        if (titleTv != null) {
            for (i in titleTv.indices) {
                tv.add(Tv(titleTv[i], descTv[i], tvImage[i]))
            }
        }
        return tv
    }
}