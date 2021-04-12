package ru.ankoks.moviessearch

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TEXT_COLOR_1 = "TEXT_COLOR_1"
        private const val TEXT_COLOR_2 = "TEXT_COLOR_2"
        private const val TEXT_COLOR_3 = "TEXT_COLOR_3"
    }

    private val textView1 by lazy {
        findViewById<TextView>(R.id.txtView1)
    }
    private val textView2 by lazy {
        findViewById<TextView>(R.id.txtView2)
    }
    private val textView3 by lazy {
        findViewById<TextView>(R.id.txtView3)
    }

    private val img1 by lazy {
        findViewById<ImageView>(R.id.episode_1_img)
    }
    private val img2 by lazy {
        findViewById<ImageView>(R.id.episode_2_img)
    }
    private val img3 by lazy {
        findViewById<ImageView>(R.id.episode_3_img)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            textView1.setTextColor(it.getInt(TEXT_COLOR_1))
            textView2.setTextColor(it.getInt(TEXT_COLOR_2))
            textView3.setTextColor(it.getInt(TEXT_COLOR_3))

            if (it.getInt(TEXT_COLOR_1) != Color.BLACK) {
                img1.setBackgroundResource(R.drawable.border)
            }
            if (it.getInt(TEXT_COLOR_2) != Color.BLACK) {
                img2.setBackgroundResource(R.drawable.border)
            }
            if (it.getInt(TEXT_COLOR_3) != Color.BLACK) {
                img3.setBackgroundResource(R.drawable.border)
            }
        }

        findViewById<View>(R.id.btn1).setOnClickListener {
            btnAction("1")
        }

        findViewById<View>(R.id.btn2).setOnClickListener {
            btnAction("2")
        }

        findViewById<View>(R.id.btn3).setOnClickListener {
            btnAction("3")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(TEXT_COLOR_1, textView1.currentTextColor)
        outState.putInt(TEXT_COLOR_2, textView2.currentTextColor)
        outState.putInt(TEXT_COLOR_3, textView3.currentTextColor)
    }

    private fun btnAction(value: String) {
        setColorAndBackground(value)

        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MovieActivity.MOVIE_NUMBER, value)
        startActivity(intent)
    }

    private fun setColorAndBackground(value: String) {
        when (value) {
            "1" -> {
                textView1.setTextColor(Color.BLUE)
                img1.setBackgroundResource(R.drawable.border)

                textView2.setTextColor(Color.BLACK)
                img2.setBackgroundResource(0)

                textView3.setTextColor(Color.BLACK)
                img3.setBackgroundResource(0)
            }
            "2" -> {
                textView1.setTextColor(Color.BLACK)
                img1.setBackgroundResource(0)

                textView2.setTextColor(Color.BLUE)
                img2.setBackgroundResource(R.drawable.border)

                textView3.setTextColor(Color.BLACK)
                img3.setBackgroundResource(0)
            }
            "3" -> {
                textView1.setTextColor(Color.BLACK)
                img1.setBackgroundResource(0)

                textView2.setTextColor(Color.BLACK)
                img2.setBackgroundResource(0)

                textView3.setTextColor(Color.BLUE)
                img3.setBackgroundResource(R.drawable.border)
            }
        }
    }
}