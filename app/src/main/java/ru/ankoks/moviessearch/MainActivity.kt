package ru.ankoks.moviessearch

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.ankoks.moviessearch.domain.MovieInfo
import ru.ankoks.moviessearch.fragments.FavouritesFragment
import ru.ankoks.moviessearch.fragments.MovieFragment
import ru.ankoks.moviessearch.fragments.MoviesFragment
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(), MoviesFragment.OnMovieClickListener,
    MoviesFragment.OnAddToFavouriteListener {
    private lateinit var items: List<MovieInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initItems()
        showMoviesList()
        initBottomNavigation()
    }

    private fun initItems() {
        items = listOf(
            MovieInfo(
                getString(R.string.star_wars_1),
                getString(R.string.episode_1),
                R.drawable.poster_the_phantom_menace
            ),
            MovieInfo(
                getString(R.string.star_wars_2),
                getString(R.string.episode_2),
                R.drawable.poster_attack_clones
            ),
            MovieInfo(
                getString(R.string.star_wars_3),
                getString(R.string.episode_3),
                R.drawable.poster_revenge_of_the_sith
            ),
            MovieInfo(
                getString(R.string.star_wars_4),
                getString(R.string.episode_4),
                R.drawable.poster_new_hope
            ),
            MovieInfo(
                getString(R.string.star_wars_5),
                getString(R.string.episode_5),
                R.drawable.poster_the_empire_strikes_back
            ),
            MovieInfo(
                getString(R.string.star_wars_6),
                getString(R.string.episode_6),
                R.drawable.poster_return_of_the_jedi
            )
        )
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                fragment,
                MoviesFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }


    private fun showMoviesList() {
        openFragment(
            MoviesFragment(
                items
            )
        )
    }

    private fun showFavouritesList() {
        openFragment(
            FavouritesFragment(
                items
            )
        )
    }

    private fun showMoviesDetails(item: MovieInfo) {
        openFragment(
            MovieFragment.newInstanceKotlin(item)
        )
    }

    private fun initBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.homePage -> {
                        showMoviesList()
                        return@setOnNavigationItemSelectedListener true
                    }

                    R.id.favPage -> {
                        showFavouritesList()
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                false

            }
    }

    override fun onClick(movieInfo: MovieInfo) {
        items.forEach { element ->
            element.clicked = false
        }
        movieInfo.clicked = true

        showMoviesDetails(movieInfo)
    }

    override fun onAddToFavourite(movieInfo: MovieInfo, imageView: ImageView) {
        if (movieInfo.isFavourite) {
            movieInfo.isFavourite = false
            imageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            Toast.makeText(this, "Successfully deleted from favorites", Toast.LENGTH_SHORT).show()
        } else {
            movieInfo.isFavourite = true
            imageView.setImageResource(R.drawable.ic_baseline_favorite_24)
            Toast.makeText(this, "Successfully added to favorites", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            customDialog()?.show()
        } else {
            super.onBackPressed()
        }
    }

    private fun customDialog(): AlertDialog? {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit dialog")
            .setMessage("Do you really want to exit?")
            .setIcon(R.drawable.ic_baseline_mood_bad_24)
            .setPositiveButton("No") { dialog, id ->
                dialog.cancel()
            }
            .setNegativeButton("Yes") { dialog, id ->
                exitProcess(0)
            }
        return builder.create()
    }
}
