package com.artelsv.petprojectsecond.di.module

import com.artelsv.petprojectsecond.ui.moviedetail.MovieDetailFragment
import com.artelsv.petprojectsecond.ui.movielist.MovieListFragment
import com.artelsv.petprojectsecond.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributesMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributesMovieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector
    abstract fun contributesProfileFragment(): ProfileFragment
}