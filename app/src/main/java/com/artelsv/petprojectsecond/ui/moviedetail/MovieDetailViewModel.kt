package com.artelsv.petprojectsecond.ui.moviedetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.icu.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.artelsv.petprojectsecond.R
import com.artelsv.petprojectsecond.domain.model.DateReleaseResult
import com.artelsv.petprojectsecond.domain.model.MovieDetail
import com.artelsv.petprojectsecond.domain.usecases.GetMovieDateReleaseUseCase
import com.artelsv.petprojectsecond.domain.usecases.GetMovieDetailsUseCase
import com.artelsv.petprojectsecond.ui.base.BaseViewModel
import com.artelsv.petprojectsecond.utils.Constants
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class MovieDetailViewModel @Inject constructor(
    private val context: Context,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieDateReleaseUseCase: GetMovieDateReleaseUseCase
) : BaseViewModel() {
    private val mMovie = MutableLiveData<MovieDetail>(null)
    val movie: LiveData<MovieDetail> = mMovie

    private val mDateRelease = MutableLiveData<DateReleaseResult>(null)
    val dateRelease: LiveData<DateReleaseResult> = mDateRelease

    val loading = MutableLiveData(true)
    val error = MutableLiveData(false)

    fun getMovieDetail(movieId: Int) {
        compositeDisposable.add(
            getMovieDetailsUseCase
                .invoke(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    getReleaseDate(it)
                }
                .subscribe({
                    handleSuccess()
                }, {
                    handleError(it)
                })
        )
    }

    private fun getReleaseDate(movieDetail: MovieDetail, iso: String = DEFAULT_ISO) : Single<MovieDetail> {
        compositeDisposable.add(
            getMovieDateReleaseUseCase
                .invoke(movieDetail.id, iso)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    initData(movieDetail, it)
                }, {
                    handleError(it)
                })
        )

        return Single.just(movieDetail)
    }

    val imageUrl: MutableLiveData<String> = MutableLiveData("")
    val voteAsString: MutableLiveData<String> = MutableLiveData("")
    val voteColor: MutableLiveData<Int> = MutableLiveData(R.color.red)
    val movieName: MutableLiveData<String> = MutableLiveData("")
    val genresAsString: MutableLiveData<String> = MutableLiveData("")

    private fun initData(movieDetail: MovieDetail, result: DateReleaseResult) {
        mDateRelease.postValue(result)
        mMovie.postValue(movieDetail)

        imageUrl.postValue(Constants.BASE_IMAGE_URL + movieDetail.backdropPath)
        voteAsString.postValue(movieDetail.voteAverage.toString())
        voteColor.postValue(
            when (movieDetail.voteAverage) {
                in 0.0..5.0 -> R.color.red
                in 5.1..7.0 -> R.color.yellow
                in 7.1..10.0 -> R.color.green
                else -> R.color.red
            }
        )
        getMovieName(context.resources, movieDetail, result)
        genresAsString.postValue(getGenresAsString(context.resources, movieDetail))
    }

    @SuppressLint("SimpleDateFormat")
    fun getMovieName(resources: Resources, movieDetail: MovieDetail, release: DateReleaseResult) {
        val inputFormat = SimpleDateFormat(DATE_FORMAT)
        val outputFormat = SimpleDateFormat(DATE_YEAR)
        var date: Date? = null

        if (!release.releaseDates.isNullOrEmpty()) {
            date = inputFormat.parse(release.releaseDates.first().releaseDate)
        }

        movieName.postValue(
            if (date != null) {
                val formattedDate = outputFormat.format(date)

                resources.getString(R.string.movie_name_pattern, movieDetail.title, formattedDate)
            } else {
                movieDetail.title
            }
        )
    }

    fun getGenresAsString(resources: Resources, movieDetail: MovieDetail): String =
        movieDetail.genres.joinToString(separator = resources.getString(R.string.movie_detail_separator)) {
            it.name
        }

    private fun handleSuccess() {
        loading.postValue(false)
        error.postValue(false)
    }

    private fun handleError(throwable: Throwable) {
        loading.postValue(false)
        error.postValue(true)

        Timber.tag(this.toString()).e(throwable.message.toString())
    }

    companion object {
        private const val DEFAULT_ISO = "RU"
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val DATE_YEAR = "yyyy"
    }
}