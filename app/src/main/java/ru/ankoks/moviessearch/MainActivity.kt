package ru.ankoks.moviessearch

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.domain.MovieInfo
import ru.ankoks.moviessearch.domain.MovieList
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    companion object {
        private const val POSITION_PRESSED = "POSITION_PRESSED"
        private const val REQUEST_CODE = 101
    }

    private val recycler by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val favourite by lazy { findViewById<View>(R.id.favouriteBtn) }

    private var positionPressed: Int = -1

    private lateinit var items: List<MovieInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = mutableListOf(
            MovieInfo(
                getString(R.string.star_wars_1),
                getString(R.string.episode_1),
                R.drawable.the_phantom_menace
            ),
            MovieInfo(
                getString(R.string.star_wars_2),
                getString(R.string.episode_2),
                R.drawable.attack_clones
            ),
            MovieInfo(
                getString(R.string.star_wars_3),
                getString(R.string.episode_3),
                R.drawable.revenge_of_the_sith
            ),
            MovieInfo(
                getString(R.string.star_wars_4),
                getString(R.string.episode_4),
                R.drawable.new_hope
            ),
            MovieInfo(
                getString(R.string.star_wars_5),
                getString(R.string.episode_5),
                R.drawable.the_empire_strikes_back
            ),
            MovieInfo(
                getString(R.string.star_wars_6),
                getString(R.string.episode_6),
                R.drawable.return_of_the_jedi
            )
        )

        initRecycler()

        savedInstanceState?.let {
            val position = it.getInt(POSITION_PRESSED)

            if (position != -1) {
                positionPressed = position
                items[position].clicked = true
                recycler.adapter?.notifyItemChanged(position)
            }
        }

        favourite.setOnClickListener {
            val intent = Intent(this, FavouriteActivity::class.java)

            intent.putExtra(FavouriteActivity.MOVIE_LIST, MovieList(items))

            startActivity(intent)
        }
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = MovieAdapter(
            items,
            false,
            fun(movieInfo: MovieInfo, position: Int) {
                movieAction(movieInfo, position)
                positionPressed = position
            },
            fun(movieInfo: MovieInfo, imageView: ImageView) {
                if (movieInfo.isFavourite) {
                    movieInfo.isFavourite = false
                    imageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                } else {
                    movieInfo.isFavourite = true
                    imageView.setImageResource(R.drawable.ic_baseline_favorite_24)
                }

                recycler.adapter?.notifyDataSetChanged()
            })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(POSITION_PRESSED, positionPressed)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val stringExtra = data?.getStringExtra("logMsg") ?: "no content"
                Toast.makeText(this, stringExtra, Toast.LENGTH_SHORT).show()
                Log.d("onActivityResult", stringExtra)

                val pos = data?.getIntExtra("POSITION", -1)

                pos?.let { it ->
                    items.forEach { element ->
                        element.clicked = false
                    }
                    items[it].clicked = true
                    recycler.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onBackPressed() {
        customDialog()?.show()
    }

    private fun customDialog(): AlertDialog? {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit dialog")
            .setMessage("Do you really want to exit?")
            .setIcon(R.drawable.ic_baseline_mood_bad_24)
            .setPositiveButton("No") { dialog, id ->
                dialog.cancel()
            }
            .setNegativeButton("Yes") { dialog, id ->
                exitProcess(0)
            }
        return builder.create()
    }

    private fun movieAction(movieInfo: MovieInfo, position: Int) {
        val intent = Intent(this, MovieActivity::class.java)

        intent.putExtra(MovieActivity.MOVIE_INFO, movieInfo)
        intent.putExtra(MovieActivity.MOVIE_NUMBER, position)

        startActivityForResult(intent, REQUEST_CODE)
    }
}