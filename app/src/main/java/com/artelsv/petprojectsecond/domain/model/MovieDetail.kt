package com.artelsv.petprojectsecond.domain.model

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genreIds: List<Int>?,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int,
//    val belongsToCollection: String?,
    val genres: List<Genre>,
    val homepage: String?,
    val imdbId: String?,
    val productionCompanies: List<Company>,
    val productionCountries: List<Country>,
    val spokenLanguages: List<Language>,
    val status: String,
    val tagline: String?,

    val favorite: Boolean = false,
    val rating: Float = 0f
)