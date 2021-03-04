package com.rivaldo.moviecatalog.tvlist

import com.rivaldo.moviecatalog.database.Tv
import com.rivaldo.moviecatalog.R
import java.util.ArrayList

object TvData {

    private val titleTv = arrayOf("Arrow",
            "Doom Patrol",
            "Dragon Ball",
            "Family Guy",
            "Flash",
            "Gotham",
            "Grey's Anatomy",
            "Hanna",
            "Naruto Shippuden",
            "NCIS")
    private val tvImage = intArrayOf(
            R.drawable.tv_poster_arrow,
            R.drawable.tv_poster_doom_patrol,
            R.drawable.tv_poster_dragon_ball,
            R.drawable.tv_poster_family_guy,
            R.drawable.tv_poster_flash,
            R.drawable.tv_poster_gotham,
            R.drawable.tv_poster_grey_anatomy,
            R.drawable.tv_poster_hanna,
            R.drawable.tv_poster_naruto_shipudden,
            R.drawable.tv_poster_ncis

    )
    private val descTv =  arrayOf("Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
        "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
    "Dahulu kala di pegunungan, seorang master pertempuran yang dikenal sebagai Gohan menemukan seorang bocah aneh yang ia beri nama Goku. Gohan membesarkannya dan melatih Goku dalam seni bela diri sampai dia mati. Bocah muda dan sangat kuat itu sendirian, tetapi mudah dikelola. Kemudian suatu hari, Goku bertemu dengan seorang gadis remaja bernama Bulma, yang pencariannya untuk bola naga membawanya ke rumah Goku. Bersama-sama, mereka berangkat untuk menemukan ketujuh bola naga dalam sebuah petualangan yang akan mengubah hidup Goku selamanya. Lihat bagaimana Goku bertemu teman-teman seumur hidupnya Bulma, Yamcha, Krillin, Master Roshi dan banyak lagi.",
    "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ). Anggota terakhir keluarga itu adalah Brian - anjing yang bisa bicara dan lebih dari sekadar hewan peliharaan, ia menjaga Stewie, sementara menghirup Martinis dan memilah-milah masalah hidupnya sendiri.",
            "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya \"manusia meta\" yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
    "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka - persona yang lebih besar dari kehidupan yang akan menjadi Catwoman, The Penguin, The Riddler, Two-Face dan The Joker?",
    "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.",
    "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
    "Naruto Shippuden adalah sebuah seri anime yang diadaptasi dari bagian II manga Naruto. Serial ini disutradarai oleh Hayato Date dan diproduksi oleh Studio Pierrot dan TV Tokyo. Pada bagian ini, pergerakan organisasi Akatsuki semakin terlihat. Naruto Shippuden juga telah dibuat versi movie-nya dengan judul Naruto Shippuden The Movie yang dirilis di Jepang pada Agustus 2007. Film ini menceritakan tentang usaha ninja jahat dalam membangkitkan Mouryou, roh jahat yang telah dikalahkan 20 tahun yang lalu. Setelah Naruto Shippuden The Movie dirilis, hadir pula Naruto Shippuden The Movie 2: Bond. Film ini menceritakan serangan kelompok ninja dari Negara Langit kepada Konoha. Kemudian Naruto Shippūden The Movie 3: Inheritors of the Will of Fire. Di sini diceritakan Konoha dituduh sebagai dalang penyebabnya para ninja dengan Kekkei Genkai menghilang dari Sunagakure, Kirigakure, Kumogakure dan Iwagakure. Di Indonesia, Naruto Shippuden sempat ditayangkan di Indosiar dan juga sempat ditayangkan di GTV.",
    "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.")

    public fun generateTvData(): List<Tv> {
        val tv = mutableListOf<Tv>()
        if (titleTv != null) {
            for (i in titleTv.indices) {
                tv.add(Tv(i,titleTv[i], descTv[i], tvImage[i].toString()))
            }
        }
        return tv
    }
}