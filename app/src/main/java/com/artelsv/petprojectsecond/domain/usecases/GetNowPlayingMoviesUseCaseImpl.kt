package com.artelsv.petprojectsecond.domain.usecases

import androidx.paging.PagingData
import com.artelsv.petprojectsecond.domain.MoviesRepository
import com.artelsv.petprojectsecond.domain.model.Movie
import com.artelsv.petprojectsecond.domain.model.MovieSortType
import io.reactivex.Flowable
import javax.inject.Inject

class GetNowPlayingMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetNowPlayingMoviesUseCase {
    override fun invoke(sortType: MovieSortType): Flowable<PagingData<Movie>> {
        return moviesRepository.getNowPlayingMovies(sortType)
    }
}