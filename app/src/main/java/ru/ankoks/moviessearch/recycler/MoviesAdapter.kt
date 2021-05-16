package ru.ankoks.moviessearch.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.R
import ru.ankoks.moviessearch.domain.MovieInfo

class MoviesAdapter(
    private val layoutInflater: LayoutInflater,
    private val items: List<MovieInfo>,
    private val listener: ((movieInfo: MovieInfo) -> Unit)?
) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(layoutInflater.inflate(R.layout.movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            listener?.invoke(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}