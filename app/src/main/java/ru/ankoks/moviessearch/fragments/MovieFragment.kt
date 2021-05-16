package ru.ankoks.moviessearch.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.ankoks.moviessearch.R
import ru.ankoks.moviessearch.domain.MovieInfo

class MovieFragment : Fragment() {
    companion object {
        const val TAG = "MovieDetailedFragment"
        private const val MOVIE_INFO = "MOVIE_INFO"

        fun newInstanceKotlin(movieInfo: MovieInfo): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_INFO, movieInfo)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieInfo = arguments?.getParcelable<MovieInfo>(MOVIE_INFO)

        val img = view.findViewById<ImageView>(R.id.imgResource)
        val info = view.findViewById<TextView>(R.id.info)
        val invite = view.findViewById<View>(R.id.inviteBtn)

        if (movieInfo != null) {
            img.setImageResource(movieInfo.image)
            info.text = movieInfo.description

            invite.setOnClickListener {
                inviteBtn(movieInfo.description)
            }
        }
    }

    private fun inviteBtn(descriptionText: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, "Let's see this movie?" +
                        "\ndescription: " + descriptionText
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}