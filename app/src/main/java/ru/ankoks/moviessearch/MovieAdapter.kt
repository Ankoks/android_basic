package ru.ankoks.moviessearch

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.domain.MovieInfo

class MovieAdapter(
    private val items: List<MovieInfo>,
    private val isFavourite: Boolean,
    val movieClickListener: (item: MovieInfo, position: Int) -> Unit,
    val favouriteClickListener: (item: MovieInfo, imageView: ImageView) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (isFavourite) {
            FavouriteVH(
                inflater.inflate(R.layout.favourite_item, parent, false)
            )
        } else {
            MovieVH(
                inflater.inflate(R.layout.movie_item, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieVH) {
            val movieInfo = items[position]
            holder.bind(movieInfo)

            holder.itemView.setOnClickListener {
                movieClickListener(movieInfo, position)
            }

            val favourite = holder.itemView.findViewById<ImageView>(R.id.favouriteImg)

            favourite?.setOnClickListener {
                favouriteClickListener(movieInfo, favourite)
            }
        }

        if (holder is FavouriteVH) {
            val movieInfo = items[position]
            holder.bind(movieInfo)

            holder.itemView.setOnClickListener {
                movieClickListener(movieInfo, position)
            }
        }
    }

    override fun getItemCount() = items.size
}
