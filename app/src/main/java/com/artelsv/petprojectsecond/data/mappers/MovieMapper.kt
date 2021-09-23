package com.artelsv.petprojectsecond.data.mappers

import com.artelsv.petprojectsecond.data.entity.MovieEntity
import com.artelsv.petprojectsecond.data.network.model.MovieResponse
import com.artelsv.petprojectsecond.domain.model.Movie
import com.artelsv.petprojectsecond.domain.model.MovieType

object MovieMapper {

    fun toMovieEntity(movie: MovieResponse, type: MovieType) : MovieEntity {
        return MovieEntity(
            adult = movie.adult,
            backdropPath = movie.backdropPath,
            budget = movie.budget,
            genreIds = movie.genreIds,
            id = movie.id,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            revenue = movie.revenue,
            runtime = movie.runtime,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            movieType = type
        )
    }

    fun toMovie(movie: MovieResponse, type: MovieType) : Movie {
        return Movie(
            adult = movie.adult,
            backdropPath = movie.backdropPath,
            budget = movie.budget,
            genreIds = movie.genreIds,
            id = movie.id,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            revenue = movie.revenue,
            runtime = movie.runtime,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            movieType = type
        )
    }

    fun toMovie(movie: MovieEntity, type: MovieType? = null) : Movie {
        return Movie(
            adult = movie.adult,
            backdropPath = movie.backdropPath,
            budget = movie.budget,
            genreIds = movie.genreIds,
            id = movie.id,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            revenue = movie.revenue,
            runtime = movie.runtime,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            movieType = type ?: movie.movieType
        )
    }
}