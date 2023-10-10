package com.example.mymovies.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.core.utils.Url
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailFragmentArgs.fromBundle(arguments as Bundle).Data

        detailViewModel.getFilmById(data.filmId).observe(viewLifecycleOwner) { film->
            if (film != null) {
                Log.e("Detail", film.isFavorite.toString())
                setIvFavorite(film.isFavorite)
                binding.ivFavorite.setOnClickListener {
                    if (film.isFavorite == null) {
                        data.isFavorite = true
                        detailViewModel.insertFavFilm(data)
                    } else {
                        data.isFavorite = false
                        detailViewModel.deleteFilm(data)
                    }
                }
            }
        }

        binding.apply {
            Glide.with(requireActivity())
                .load(Url.image + data.image)
                .into(ivFilm)
            tvTitle.text = data.title
            tvRelease.text =
                resources.getText(R.string.release_date).toString() + " " + data.releaseDate
            tvAdult.text =
                resources.getText(R.string.adult).toString() + " " + data.adult.toString()
            tvOverview.text = data.overview
        }

    }

    private fun setIvFavorite(favorite: Boolean?) {
        if (favorite == true) {
            binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

}