package ru.ankoks.moviessearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.domain.MovieInfo

class MovieAdapter(private val items: List<MovieInfo>, val clickListener: (item: MovieInfo, position: Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_item, parent, false)
        val vh = MovieVH(view);

        return vh;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieVH) {
            val movieItem = items[position]
            holder.bind(movieItem)
            holder.itemView.setOnClickListener {
                clickListener(movieItem, position)
            }
        }
    }

    override fun getItemCount() = items.size
}
