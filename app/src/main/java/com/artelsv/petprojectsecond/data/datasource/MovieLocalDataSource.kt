package com.artelsv.petprojectsecond.data.datasource

import com.artelsv.petprojectsecond.data.database.dao.MovieDao
import com.artelsv.petprojectsecond.data.mappers.MovieMapper
import com.artelsv.petprojectsecond.domain.model.DateReleaseResult
import com.artelsv.petprojectsecond.domain.model.Movie
import com.artelsv.petprojectsecond.domain.model.MovieDetail
import com.artelsv.petprojectsecond.domain.model.MovieType
import io.reactivex.Single
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) : MovieDataSource {

    override fun getPopularMovies(page: Int): Single<List<Movie>> { // добавить логику для обработки страницы через локульную бд
        return movieDao.getAllMoviesByType(MovieType.POPULAR).map { it.map(MovieMapper::toMovie) }
    }

    override fun getNowPlayingMovies(page: Int): Single<List<Movie>> {
        return movieDao.getAllMoviesByType(MovieType.NOW_PLAYING).map { it.map(MovieMapper::toMovie) }
    }

    override fun getMovieDateRelease(movieId: Int): Single<List<DateReleaseResult>> {
        return Single.error(Throwable("Проверка ошибки"))
    }

    override fun getMovieDetails(movieId: Int): Single<MovieDetail> {
        return Single.error(Throwable("Проверка ошибки"))
    }

    override fun addMoviesToDb(data: List<Movie>, type: MovieType): List<Movie> {
        movieDao.addMovies(data.map { MovieMapper.toMovieEntity(it, type) })

        return data
    }

}