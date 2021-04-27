package ru.ankoks.moviessearch.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieInfo(val title: String, val description: String, var image: Int, var clicked: Boolean = false, var isFavourite: Boolean = false) : Parcelable