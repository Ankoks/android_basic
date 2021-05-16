package ru.ankoks.moviessearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.ankoks.moviessearch.domain.MovieInfo
import ru.ankoks.moviessearch.fragments.MovieFragment
import ru.ankoks.moviessearch.fragments.MoviesFragment


class MainActivity : AppCompatActivity(), MoviesFragment.OnMovieClickListener {
    private lateinit var items: List<MovieInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initItems()
        showNewsList()
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

    private fun showNewsList() {
        val moviesListFragment = MoviesFragment(
            items
        )
        /*newsListFragment.listener = this*/
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, moviesListFragment, MoviesFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    private fun showMoviesDetails(item: MovieInfo) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieFragment.newInstanceKotlin(item),
                MovieFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    private fun initBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.homePage-> {
                        showNewsList()
                        return@setOnNavigationItemSelectedListener true
                    }

                    R.id.favPage-> {
                        showNewsList()
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

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)

        if (fragment is MoviesFragment) {
            fragment.listener = this
        }
    }
}
