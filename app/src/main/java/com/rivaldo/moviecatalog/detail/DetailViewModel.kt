package com.rivaldo.moviecatalog.detail

import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    companion object{
        private lateinit var detailData : Any

        public fun setData(item:Any){
            this.detailData = item
        }
    }




    fun getData() : Any{
        return detailData
    }
}