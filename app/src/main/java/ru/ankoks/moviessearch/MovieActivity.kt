package ru.ankoks.moviessearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MovieActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_NUMBER = "MOVIE_NUMBER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieNumber = intent.getStringExtra(MOVIE_NUMBER)
        if (movieNumber == "1") {
            setContentView(R.layout.movie_1_activity)
        }
        if (movieNumber == "2") {
            setContentView(R.layout.movie_2_activity)
        }
        if (movieNumber == "3") {
            setContentView(R.layout.movie_3_activity)
        }
    }
}