package ru.ankoks.moviessearch.domain

import java.io.Serializable

class MovieInfo(val title: String, val description: String, val image: Int, var clicked: Boolean = false) : Serializable