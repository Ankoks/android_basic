package ru.ankoks.moviessearch.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieInfo(val src: Int, val description: Int) : Parcelable