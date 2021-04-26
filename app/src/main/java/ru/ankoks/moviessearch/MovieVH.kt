package ru.ankoks.moviessearch

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.domain.MovieInfo

class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.itemText)
    val img = itemView.findViewById<ImageView>(R.id.itemImg)

    fun bind(movieInfo: MovieInfo) {
        title.text = movieInfo.title
        img.setImageResource(movieInfo.image)
    }
}