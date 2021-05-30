package ru.ankoks.moviessearch.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.R
import ru.ankoks.moviessearch.domain.MovieInfo

class MoviesAdapter(
    private val items: List<MovieInfo>,
    private val isFavourite: Boolean,
    private val listener: ((movieInfo: MovieInfo) -> Unit),
    private val favouriteClickListener: (item: MovieInfo, imageView: ImageView) -> Unit
) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (isFavourite) {
            MoviesViewHolder(inflater.inflate(R.layout.favourite_item, parent, false))
        } else {
            MoviesViewHolder(inflater.inflate(R.layout.movie_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movieInfo = items[position]

        holder.bind(movieInfo)
        holder.itemView.setOnClickListener {
            listener.invoke(items[position])
        }

        val favourite = holder.itemView.findViewById<ImageView>(R.id.favouriteImg)
        favourite?.setOnClickListener {
            favouriteClickListener(movieInfo, favourite)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}