package com.rivaldo.moviecatalog.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.database.Tv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Movie::class, Tv::class],
    version = 1,
    exportSchema = false
)
abstract class MovieCatalogDatabase : RoomDatabase() {
    abstract fun movieCatalogDao(): CatalogDao

    companion object {
        @Volatile
        private var INSTANCE: MovieCatalogDatabase? = null

        val prepopulatedatamovie = listOf(Movie(id = 458576, title = "Monster Hunter", desc = "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.", image = "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg"),
                                    Movie(id = 464052, title = "Wonder Woman 1984", desc = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.", image = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg"),
                                    Movie(id = 587807, title = "Tom & Jerry", desc = "Jerry moves into New York City's finest hotel on the eve of the wedding of the century, forcing the desperate event planner to hire Tom to get rid of him. As mayhem ensues, the escalating cat-and-mouse battle soon threatens to destroy her career, the wedding, and possibly the hotel itself.", image = "/6KErczPBROQty7QoIsaa6wJYXZi.jpg"),
                                    Movie(id = 587996, title = "Below Zero", desc = "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.", image = "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg"),
                                    Movie(id = 590706, title = "Jiu Jitsu", desc = "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.", image = "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg")
        )

        val prepopulatedatatv = listOf(Tv(id = 69050, name = "Riverdale", desc = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.", image = "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"),
                                    Tv(id = 71712, name = "The Good Doctor", desc = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives", image = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"),
                                    Tv(id = 85271, name = "WandaVision", desc = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.", image = "/glKDfE6btIRcVB5zrjspRIs4r52.jpg"),
                                    Tv(id = 95057, name = "Superman & Lois", desc = "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.", image = "/6SJppowm7cdQgLkvoTlnTUSbjr9.jpg"),
                                    Tv(id = 97513, name = "The Boarding School: Las Cumbres", desc = "In an inaccessible place between the mountains and isolated from the world, a school is located next to an old monastery. The students are rebellious and problematic young people who live under the strict and severe discipline imposed by the center to reintegrate them into society. The surrounding forest is home to ancient legends, threats that are still valid and that will immerse them in terrifying adventures.", image = "/kl07N07l4XNjXF48oujzWXs40Dw.jpg")
        )
        fun getInstance(context: Context): MovieCatalogDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                MovieCatalogDatabase::class.java,
                "Movie.db")
                    .addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            GlobalScope.launch(Dispatchers.IO){
                                for (i in prepopulatedatamovie.indices){
                                    getInstance(context).movieCatalogDao().insertFavoriteMovie(
                                        prepopulatedatamovie[i])
                                    getInstance(context).movieCatalogDao().insertFavoriteTv(
                                        prepopulatedatatv[i])
                                }

                            }
                        }
                    })
                    .build()
            }

    }

}