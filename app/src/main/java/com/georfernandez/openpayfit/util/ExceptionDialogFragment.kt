package com.georfernandez.openpayfit.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.georfernandez.openpayfit.databinding.ExceptionDialogFragmentBinding
import com.georfernandez.openpayfit.util.Constants.EMPTY_STRING
import com.georfernandez.openpayfit.util.Constants.EXCEPTION_DIALOG_TITLE

class ExceptionDialogFragment : DialogFragment() {
    private var title: String = EMPTY_STRING
    private lateinit var binding: ExceptionDialogFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(EXCEPTION_DIALOG_TITLE)?.let { title = it }
        binding = ExceptionDialogFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exceptionDialogTitleTextView.text = title
        binding.exceptionDialogPositiveButton.setOnClickListener { this.dismiss() }
    }

    companion object {
        const val EXCEPTION_DIALOG_FRAGMENT = "EXCEPTION_DIALOG_FRAGMENT"
        fun newInstance(title: String): ExceptionDialogFragment {
            val frag = ExceptionDialogFragment()
            val args = Bundle()
            args.putString(EXCEPTION_DIALOG_TITLE, title)
            frag.arguments = args
            return frag
        }
    }
}
