package ru.ankoks.moviessearch

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.ankoks.moviessearch.domain.MovieInfo

class MovieActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_NUMBER = "MOVIE_NUMBER"
        const val MOVIE_INFO = "MOVIE_INFO"
    }

    private val image by lazy {
        findViewById<ImageView>(R.id.imgResource)
    }

    private val info by lazy {
        findViewById<TextView>(R.id.info)
    }

    private val likeRadio by lazy {
        findViewById<CheckBox>(R.id.like)
    }

    private val commentText by lazy {
        findViewById<EditText>(R.id.comment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity)

        val movieInfo = intent.getSerializableExtra(MOVIE_INFO) as MovieInfo

        image.setImageResource(movieInfo.src)
        info.text = getString(movieInfo.description)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        println(
                String.format("Like value: [%s] and user comment: [%s]",
                        likeRadio.isChecked,
                        commentText.text.toString()
                )
        )
    }
}