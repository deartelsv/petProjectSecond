package com.artelsv.petprojectsecond.data.datasource

import com.artelsv.petprojectsecond.data.network.model.MovieListResponse
import com.artelsv.petprojectsecond.data.network.model.auth.UserResponse
import com.artelsv.petprojectsecond.domain.model.MovieList
import io.reactivex.Single

interface UserDataSource {

    fun createGuestSession(): Single<String>
    fun createRequestToken(): Single<String>
    fun createSession(requestToken: String): Single<String>
    fun createSessionWithUser(
        requestToken: String,
        login: String,
        password: String
    ): Single<String>

    fun getUser(sessionId: String): Single<UserResponse>

    fun getFavoriteMovies(accountId: Int) : Single<MovieListResponse>
    fun getFavoriteTvShows(accountId: Int) : Single<MovieListResponse>
    fun getRatedMovies() : Single<MovieListResponse>
    fun getRatedTvShows() : Single<MovieListResponse>
}