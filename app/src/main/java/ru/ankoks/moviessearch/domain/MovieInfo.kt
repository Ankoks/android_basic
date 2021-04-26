package ru.ankoks.moviessearch.domain

import java.io.Serializable

class MovieInfo(val title: String, val description: String, var image: Int, var clicked: Boolean = false, var isFavourite: Boolean = false) : Serializable