package ru.ankoks.moviessearch

import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.domain.MovieInfo
import ru.ankoks.moviessearch.domain.MovieList

class FavouriteActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_LIST = "MOVIE_LIST"
    }

    private val recycler by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favourite_list)

        initRecycler()
    }

    private fun initRecycler() {
        intent.getParcelableExtra<MovieList>(MOVIE_LIST)?.let {
            val result: MutableList<MovieInfo> = mutableListOf()

            for (item in it.items) {
                if (item.isFavourite) {
                    result.add(item)
                }
            }

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            } else {
                recycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            }
            recycler.adapter = MovieAdapter(
                    result,
                    true,
                    fun(_: MovieInfo, _: Int) {},
                    fun(_: MovieInfo, _: ImageView) {}
            )
        }
    }
}