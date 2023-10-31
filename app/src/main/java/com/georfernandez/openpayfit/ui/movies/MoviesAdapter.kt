package com.georfernandez.openpayfit.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.georfernandez.domain.entity.Movie
import com.georfernandez.openpayfit.databinding.FragmentCardBinding
import com.georfernandez.openpayfit.util.showImage

class MoviesAdapter(private val movieList: List<Movie>) : RecyclerView.Adapter<MovieTabViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTabViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentCardBinding.inflate(inflater, parent, false)
        return MovieTabViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieTabViewHolder, position: Int) {
        val movieList: Movie = movieList[position]
        holder.bind(movieList)
    }

    override fun getItemCount(): Int = movieList.size
}

class MovieTabViewHolder(private val binding: FragmentCardBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movieItem: Movie) {
        binding.showImage.showImage(movieItem.posterPath)
        binding.showName.text = movieItem.title
        binding.showOverview.text = movieItem.overview
    }
}
