package com.artelsv.petprojectsecond.data.datasource

import com.artelsv.petprojectsecond.data.mappers.MovieDateResultMapper
import com.artelsv.petprojectsecond.data.mappers.MovieDetailMapper
import com.artelsv.petprojectsecond.data.mappers.MovieMapper
import com.artelsv.petprojectsecond.data.network.MoviesService
import com.artelsv.petprojectsecond.domain.model.DateReleaseResult
import com.artelsv.petprojectsecond.domain.model.Movie
import com.artelsv.petprojectsecond.domain.model.MovieDetail
import com.artelsv.petprojectsecond.domain.model.MovieType
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val moviesService: MoviesService) : MovieDataSource {

    override fun getPopularMovies(page: Int): Single<List<Movie>> =
        moviesService.getPopularMovies(page).map { it.results.map(MovieMapper::toMovie) }

    override fun getNowPlayingMovies(page: Int): Single<List<Movie>> =
        moviesService.getNowPlayingMovies(page).map { it.results.map(MovieMapper::toMovie) }

    override fun getMovieDateRelease(movieId: Int): Single<List<DateReleaseResult>> =
        moviesService.getMovieReleaseDates(movieId)
            .map { it.results.map(MovieDateResultMapper::toDateReleaseResult) }

    override fun getMovieDetails(movieId: Int): Single<MovieDetail> =
        moviesService.getMovieDetail(movieId).map(MovieDetailMapper::toMovieDetail)

    override fun addMoviesToDb(data: List<Movie>, type: MovieType): List<Movie> {
        return listOf() // выглядит как костыль, но пусть будет так
    }
}