package ru.ankoks.moviessearch.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieList(val items: List<MovieInfo>) : Parcelable