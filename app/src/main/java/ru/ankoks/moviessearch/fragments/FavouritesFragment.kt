package ru.ankoks.moviessearch.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.ankoks.moviessearch.R
import ru.ankoks.moviessearch.domain.MovieInfo
import ru.ankoks.moviessearch.recycler.MoviesAdapter

class FavouritesFragment(var items: List<MovieInfo>) : Fragment() {
    companion object {
        const val TAG = "FavouritesFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val result: MutableList<MovieInfo> = mutableListOf()

        for (item in items) {
            if (item.isFavourite) {
                result.add(item)
            }
        }

        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = MoviesAdapter(
            result,
            true,
            fun(_: MovieInfo) {},
            fun(_: MovieInfo, _: ImageView) {}
        )
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