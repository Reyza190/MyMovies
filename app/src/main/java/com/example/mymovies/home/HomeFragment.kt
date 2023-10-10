package com.example.mymovies.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.data.Resource
import com.example.mymovies.R
import com.example.core.ui.FilmAdapter
import com.example.mymovies.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmAdapter = FilmAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it
                )
            )
        }

        setRecycleView(filmAdapter)
        setSearchView()


        //navigate to favorite fragment
        navigateToFavorite()

        homeViewModel.film.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Error -> Log.e("MainActivity", it.message.toString())
                    is Resource.Loading -> Log.e("MainActivity", "loading")
                    is Resource.Success -> {
                        filmAdapter.setData(it.data)
                        Log.e("MainActivity", it.data.toString())
                    }
                }
            }
        }

        homeViewModel.searchFilm.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Error -> Log.e("MainActivity", it.message.toString())
                    is Resource.Loading -> Log.e("MainActivity", "loading")
                    is Resource.Success -> {
                        filmAdapter.setData(it.data)
                        Log.e("MainActivity", it.data.toString())
                    }
                }
            }
        }
    }


    private fun navigateToFavorite() {
        binding.ivFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }

    private fun setSearchView() {
        binding.searchView.apply {
            queryHint = context?.getString(R.string.query_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    homeViewModel.setSearch(p0.toString())
                    return true
                }
            })
        }
    }

    private fun setRecycleView(filmAdapter: FilmAdapter) {
        with(binding.rvListFilm) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = filmAdapter
        }
    }

}