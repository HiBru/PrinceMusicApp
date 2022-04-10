package de.hicedevelopments.princemusicapp.app

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

abstract class BaseFragment : Fragment() {

    private val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    open fun onBackPressed() {
        // remove callback and use onBackPressed() in HostActivity if fragment does not override this method
        backPressedCallback.remove()
        requireActivity().onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this as LifecycleOwner,
            backPressedCallback
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressedCallback.remove()
    }
}