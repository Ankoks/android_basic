package ru.ankoks.moviessearch

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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
        intent.getSerializableExtra(MOVIE_LIST)?.let {
            val movies = it as MovieList
            val result: MutableList<MovieInfo> = mutableListOf()

            for (item in movies.items) {
                if (item.isFavourite) {
                    result.add(item)
                }
            }

            recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = MovieAdapter(
                    result,
                    fun(item: MovieInfo, position: Int) {},
                    fun(item: MovieInfo, imageView: ImageView) {}
            )
        }
    }
}