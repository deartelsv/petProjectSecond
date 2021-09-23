package com.artelsv.petprojectsecond.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.artelsv.petprojectsecond.databinding.FragmentMovieDetailBinding
import com.artelsv.petprojectsecond.di.factory.ViewModelFactory
import com.artelsv.petprojectsecond.ui.Screens
import com.github.terrakok.cicerone.Router
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var router: Router

    private val binding: FragmentMovieDetailBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        arguments?.let {
            viewModel.setMovieValue(it[MOVIE_ID] as Int)
        }

        setObservers(binding)

        return binding.root
    }

    private fun setObservers(binding: FragmentMovieDetailBinding) {
        viewModel.movie.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.ivPoster.load(viewModel.getImageUrl(it))
            }
        })

        viewModel.error.observe(viewLifecycleOwner, {
            if (it != null && it) {
                Toast.makeText(requireContext(), ERROR, Toast.LENGTH_LONG).show()
                router.backTo(Screens.movieList())
            }
        })
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val ERROR = "Ошибка, повторите попытку" // пока тут

        fun newInstance(movieId: Int) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(MOVIE_ID, movieId)
            }
        }
    }
}