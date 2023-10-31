package com.georfernandez.openpayfit.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.georfernandez.domain.entity.MovieStarring
import com.georfernandez.openpayfit.databinding.FragmentCardBinding
import com.georfernandez.openpayfit.util.showImage

class ProfileAdapter(private val movieStarringList: List<MovieStarring>) :
    RecyclerView.Adapter<ProfileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentCardBinding.inflate(inflater, parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val showItem = movieStarringList[position]
        holder.bind(showItem)
    }

    override fun getItemCount(): Int = movieStarringList.size
}

class ProfileViewHolder(private val binding: FragmentCardBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieStarring) {
        binding.showImage.showImage(item.posterPath)
        binding.showName.text = if (item.mediaType == MEDIA_TYPE_MOVIE) item.title else item.name
        binding.showOverview.text = item.overview
    }

    companion object {
        private const val MEDIA_TYPE_MOVIE = "movie"
    }
}
