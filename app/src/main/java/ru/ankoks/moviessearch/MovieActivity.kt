package ru.ankoks.moviessearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.ankoks.moviessearch.domain.MovieInfo

class MovieActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_INFO = "MOVIE_INFO"
        const val MOVIE_NUMBER = "MOVIE_NUMBER"
    }

    private var position: Int = -1

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

    private val invite by lazy {
        findViewById<TextView>(R.id.inviteBtn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_info)

        val movieInfo = intent.getParcelableExtra<MovieInfo>(MOVIE_INFO)!!
        position = intent.getIntExtra(MOVIE_NUMBER, -1)

        image.setImageResource(movieInfo.image)

        val descriptionText = movieInfo.description
        info.text = descriptionText

        invite.setOnClickListener {
            inviteBtn(descriptionText)
        }
    }

    private fun inviteBtn(descriptionText: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, "Let's see this movie?" +
                        "\ndescription: " + descriptionText
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onBackPressed() {
        setResult(
            Activity.RESULT_OK,
            Intent()
                .putExtra(
                    "logMsg",
                    String.format(
                        "Like value: [%s] and user comment: [%s]",
                        likeRadio.isChecked,
                        commentText.text.toString()
                    )
                )
                .putExtra("POSITION", position)
        )

        super.onBackPressed()
    }
}