package com.georfernandez.openpayfit.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.georfernandez.domain.entity.Actor
import com.georfernandez.openpayfit.databinding.FragmentProfileBinding
import com.georfernandez.openpayfit.util.ExceptionDialogFragment
import com.georfernandez.openpayfit.util.ExceptionDialogFragment.Companion.EXCEPTION_DIALOG_FRAGMENT
import com.georfernandez.openpayfit.util.showImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel.getState().observe(viewLifecycleOwner, ::updateUi)
        profileViewModel.fetchProfileInfo()
        return binding.root
    }

    private fun updateUi(profileData: ProfileViewModel.ProfileData?) {
        if (profileData != null) {
            when (profileData.state) {
                ProfileViewModel.ProfileState.SHOW_INFO -> showProfileInfo(profileData.mostPopularActor)
                ProfileViewModel.ProfileState.ON_ERROR -> {
                    ExceptionDialogFragment.newInstance("Error").show(
                        childFragmentManager,
                        EXCEPTION_DIALOG_FRAGMENT,
                    )
                    binding.loaderAnimation.visibility = View.GONE
                }

                ProfileViewModel.ProfileState.EMPTY_STATE -> {
                    binding.emptyState.visibility = View.VISIBLE
                    binding.contentContainer.visibility = View.GONE
                    binding.loaderAnimation.visibility = View.GONE
                }

                ProfileViewModel.ProfileState.LOADING -> {
                    binding.loaderAnimation.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showProfileInfo(mostPopularActor: Actor?) {
        mostPopularActor?.let { actor ->
            binding.emptyState.visibility = View.GONE
            binding.contentContainer.visibility = View.VISIBLE
            binding.loaderAnimation.visibility = View.GONE
            binding.actorImage.showImage(actor.profilePath)
            binding.actorName.text = actor.name
            binding.actorBiography.text = actor.biography
            binding.recycler.apply {
                adapter = ProfileAdapter(actor.movieStarring)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}
