package com.example.viewModel.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewModel.R
import com.example.viewModel.retrofit.models.Movie


class MovieListFragment : Fragment() {

    private lateinit var movieViewModel:MoviesViewModel
    private lateinit var movieAdapter:MovieRecyclerViewAdapter
    private var popularMovies:List<Movie> = ArrayList()


    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_movie_list_list, container, false)

            observer()


        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = movieAdapter
            }
        }


        return view
    }
    fun observer(){
        movieViewModel=ViewModelProvider(this).get(MoviesViewModel::class.java)
        movieAdapter= MovieRecyclerViewAdapter()


        //observer de las peliculas
        movieViewModel.getPopularmovies().observe(viewLifecycleOwner, Observer {
            popularMovies=it
            movieAdapter.setData(popularMovies)
        })
    }


}