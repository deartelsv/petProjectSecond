package com.artelsv.petprojectsecond.domain.repository

import com.artelsv.petprojectsecond.domain.model.movie.MovieList
import com.artelsv.petprojectsecond.domain.model.movie.RateMovie
import com.artelsv.petprojectsecond.domain.model.movie.ToggleFavorite
import io.reactivex.Single

interface UserMoviesRepository {

    fun getFavoriteMovies(accountId: Int): Single<MovieList>
    fun getFavoriteTvShows(accountId: Int): Single<MovieList>
    fun getRatedMovies(accountId: Int): Single<MovieList>
    fun getRatedTvShows(accountId: Int): Single<MovieList>

    fun toggleFavorite(data: ToggleFavorite, accountId: Int): Single<Boolean>
    fun rateMovie(data: RateMovie, movieId: Int): Single<Boolean>
}