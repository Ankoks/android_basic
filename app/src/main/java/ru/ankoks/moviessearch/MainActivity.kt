package ru.ankoks.moviessearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btn1).setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra(MovieActivity.MOVIE_NUMBER, "1")
            startActivity(intent)
        }

        findViewById<View>(R.id.btn2).setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra(MovieActivity.MOVIE_NUMBER, "2")
            startActivity(intent)
        }

        findViewById<View>(R.id.btn3).setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra(MovieActivity.MOVIE_NUMBER, "3")
            startActivity(intent)
        }
    }
}