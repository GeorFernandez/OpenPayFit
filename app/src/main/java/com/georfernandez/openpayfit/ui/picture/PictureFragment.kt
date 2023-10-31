package com.georfernandez.openpayfit.ui.picture

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.georfernandez.openpayfit.R
import com.georfernandez.openpayfit.databinding.FragmentPictureBinding
import com.georfernandez.openpayfit.util.ExceptionDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureFragment : Fragment() {
    private val viewModel: PictureViewModel by viewModels()
    private lateinit var binding: FragmentPictureBinding
    private val galleryIntentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val picturesSelection = mutableListOf<Bitmap>()

            if (result.data?.clipData != null) {
                // If the user selected multiple images
                val count: Int = result?.data?.clipData?.itemCount ?: 0
                for (i in 0 until count) {
                    val imageUri: Uri? = result.data?.clipData?.getItemAt(i)?.uri

                    imageUri?.let {
                        val bitmap = BitmapFactory.decodeStream(context?.contentResolver?.openInputStream(imageUri))
                        picturesSelection.add(bitmap)
                    }
                }
            } else if (result.data?.data != null) {
                // If the user selected only one image
                val imageUri: Uri? = result.data?.data

                imageUri?.let {
                    val bitmap = BitmapFactory.decodeStream(context?.contentResolver?.openInputStream(imageUri))
                    picturesSelection.add(bitmap)
                }
            }
            viewModel.savePictures(picturesSelection)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPictureBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner, ::updateUi)
        binding.galleryButton.setOnClickListener { viewModel.onGalleryPictureSelection() }
        binding.saveButton.setOnClickListener {
            context?.let { ctx ->
                viewModel.onSaveSelection(isNetworkConnected(ctx))
            }
        }
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = connectivityManager?.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }

    private fun updateUi(data: PictureViewModel.PictureData) {
        when (data.state) {
            PictureViewModel.PictureState.GALLERY -> openGallery()
            PictureViewModel.PictureState.SAVED_PICTURES -> Toast.makeText(
                context,
                getString(R.string.successfully_saved_toast),
                Toast.LENGTH_SHORT,
            )
                .show()

            PictureViewModel.PictureState.ON_ERROR -> {
                ExceptionDialogFragment.newInstance("Error").show(
                    childFragmentManager,
                    ExceptionDialogFragment.EXCEPTION_DIALOG_FRAGMENT,
                )
            }
        }
    }

    @SuppressLint("IntentReset")
    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.type = "image/*"
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = "image/*"
        val chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.gallery_selection_title))
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

        galleryIntentLauncher.launch(chooserIntent)
    }
}
