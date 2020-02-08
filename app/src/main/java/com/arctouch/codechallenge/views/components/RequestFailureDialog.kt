package com.arctouch.codechallenge.views.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.arctouch.codechallenge.R
import kotlinx.android.synthetic.main.request_failure_dialog.*

class RequestFailureDialog(
    val retry: () -> Unit,
    val cancel: () -> Unit
) : DialogFragment() {

    companion object {
        val REQUEST_FAILURE_DIALOG_TAG = "REQUEST_FAILURE_DIALOG_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.request_failure_dialog, container, false)
    }

    override fun onResume() {
        super.onResume()
        configureDialog()
    }

    private fun configureDialog() {
        btnRetry.setOnClickListener {
            dismiss()
            retry()
        }

        btnCancel.setOnClickListener {
            dismiss()
            cancel()
        }
    }

}