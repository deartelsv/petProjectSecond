package com.artelsv.petprojectsecond.domain.usecases

import com.artelsv.petprojectsecond.domain.MoviesRepository
import com.artelsv.petprojectsecond.domain.model.Movie
import com.artelsv.petprojectsecond.ui.movielist.MovieSortType
import io.reactivex.Single
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetPopularMoviesUseCase {
    override fun invoke(sortType: MovieSortType): Single<List<Movie>> {
        return when(sortType) {
            MovieSortType.ASC -> moviesRepository.getPopularMovies().map { it.sortedBy { sort -> sort.voteAverage } }
            MovieSortType.DESC -> moviesRepository.getPopularMovies().map { it.sortedByDescending { sort -> sort.voteAverage } }
        }
    }
}