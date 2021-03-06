package ru.ankoks.moviessearch.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.R
import ru.ankoks.moviessearch.domain.MovieInfo
import ru.ankoks.moviessearch.domain.MovieList
import ru.ankoks.moviessearch.recycler.MoviesAdapter

class MoviesFragment : Fragment() {
    private lateinit var items: List<MovieInfo>

    companion object {
        const val TAG = "MoviesListFragment"
        private const val ITEMS = "ITEMS"

        fun newInstance(items: List<MovieInfo>): MoviesFragment {
            return MoviesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ITEMS, MovieList(items))
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        items = (arguments?.getSerializable(ITEMS) as MovieList).items

        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = MoviesAdapter(
            items,
            false,
            fun(movieInfo: MovieInfo) {
                (requireActivity() as? OnMovieClickListener)?.onClick(movieInfo)
            },
            fun(movieInfo: MovieInfo, imageView: ImageView) {
                (requireActivity() as? OnAddToFavouriteListener)?.onAddToFavourite(
                    movieInfo,
                    imageView
                )
            }
        )

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            run {
                Toast.makeText(
                    context,
                    bundle.getString("bundleKey").toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    interface OnMovieClickListener {
        fun onClick(movieInfo: MovieInfo)
    }

    interface OnAddToFavouriteListener {
        fun onAddToFavourite(movieInfo: MovieInfo, imageView: ImageView)
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "onViewStateRestored")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }
}