package ru.ankoks.moviessearch.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import ru.ankoks.moviessearch.R
import ru.ankoks.moviessearch.domain.MovieInfo

class MovieFragment : Fragment() {
    companion object {
        const val TAG = "MovieDetailedFragment"
        private const val MOVIE_INFO = "MOVIE_INFO"

        fun newInstanceKotlin(movieInfo: MovieInfo): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MOVIE_INFO, movieInfo)
                }
            }
        }
    }

    private lateinit var likeRadio: CheckBox
    private lateinit var commentText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_info_coordinate_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieInfo = arguments?.getSerializable(MOVIE_INFO) as MovieInfo

        val img = view.findViewById<ImageView>(R.id.imgResource)
        val info = view.findViewById<TextView>(R.id.info)
        val invite = view.findViewById<View>(R.id.inviteBtn)

        likeRadio = view.findViewById(R.id.like)
        commentText = view.findViewById(R.id.comment)

        img.setImageResource(movieInfo.image)
        info.text = movieInfo.description

        invite.setOnClickListener {
            inviteBtn(movieInfo.description)
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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(MovieFragment.TAG, "onViewStateRestored")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(MovieFragment.TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(MovieFragment.TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(MovieFragment.TAG, "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(MovieFragment.TAG, "onDestroyView")
        setFragmentResult(
            "requestKey",
            bundleOf(
                "bundleKey" to
                        String.format(
                            "Like value: [%s] and user comment: [%s]",
                            likeRadio.isChecked,
                            commentText.text.toString()
                        )
            )
        )
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(MovieFragment.TAG, "onDetach")
    }
}
