package ru.ankoks.moviessearch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.domain.MovieInfo

class MainActivity : AppCompatActivity() {
    companion object {
        private const val POSITION_PRESSED = "POSITION_PRESSED"
    }

    private val recycler by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private var positionPressed: Int = 0

    private lateinit var items: List<MovieInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = mutableListOf(
                MovieInfo(getString(R.string.star_wars_1), getString(R.string.episode_1), R.drawable.the_phantom_menace),
                MovieInfo(getString(R.string.star_wars_2), getString(R.string.episode_2), R.drawable.attack_clones),
                MovieInfo(getString(R.string.star_wars_3), getString(R.string.episode_3), R.drawable.revenge_of_the_sith),
                MovieInfo(getString(R.string.star_wars_4), getString(R.string.episode_4), R.drawable.new_hope),
                MovieInfo(getString(R.string.star_wars_5), getString(R.string.episode_5), R.drawable.the_empire_strikes_back),
                MovieInfo(getString(R.string.star_wars_6), getString(R.string.episode_6), R.drawable.return_of_the_jedi)
        )

        initRecycler()

        savedInstanceState?.let {
            val position = it.getInt(POSITION_PRESSED)

            items[position].clicked = true
            recycler.adapter?.notifyItemChanged(position)
        }
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = MovieAdapter(items) { item, position ->
            movieAction(item)

            positionPressed = position
            item.clicked = true
            recycler.adapter?.notifyItemChanged(position)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(POSITION_PRESSED, positionPressed)
    }

    private fun movieAction(movieInfo: MovieInfo) {
        val intent = Intent(this, MovieActivity::class.java)

        intent.putExtra(MovieActivity.MOVIE_INFO, movieInfo)

        startActivity(intent)
    }
}