package com.example.favorite.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.ui.FilmAdapter
import com.example.favorite.di.DaggerFavoriteModule
import com.example.mymovies.databinding.FragmentFavoriteBinding
import com.example.mymovies.di.FavoriteDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels{
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteModule.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteDependencies::class.java
                )
            ).build().inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmAdapter = FilmAdapter{
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(it)
            )
        }

        favoriteViewModel.getFav().observe(viewLifecycleOwner){
            filmAdapter.setData(it)
        }

        setRecycleView(filmAdapter)
    }

    private fun setRecycleView(filmAdapter: FilmAdapter) {
        with(binding.rvListFilm) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = filmAdapter
        }
    }

}