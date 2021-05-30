package ru.ankoks.moviessearch.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.R
import ru.ankoks.moviessearch.domain.MovieInfo

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.itemText)
    val img = itemView.findViewById<ImageView>(R.id.itemImg)
    val favImg = itemView.findViewById<ImageView>(R.id.favouriteImg)

    fun bind(movieInfo: MovieInfo) {
        title.text = movieInfo.title
        img.setImageResource(movieInfo.image)

        if (movieInfo.clicked) {
            itemView.setBackgroundResource(R.drawable.border)
        } else {
            itemView.setBackgroundResource(0)
        }

        favImg?.let {
            if (movieInfo.isFavourite) {
                favImg.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                favImg.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }
}