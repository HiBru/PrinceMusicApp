package de.hicedevelopments.princemusicapp.ui.splash

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingAction
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingDirections
import de.hicedevelopments.princemusicapp.app.extension.showToast
import de.hicedevelopments.princemusicapp.data.model.Result
import de.hicedevelopments.princemusicapp.data.remote.NetworkErr
import de.hicedevelopments.princemusicapp.databinding.ViewSplashBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreen : ResourceFragment<ViewSplashBinding>() {

    override val layoutId = R.layout.view_splash
    override val viewModel: SplashViewModel by viewModel()

    override fun bindViewModel(binding: ViewSplashBinding) {
        //startIconAnimation()
        viewModel.results.observe(viewLifecycleOwner) {
            showToast("results completed")
            navigateToListScreen()
        }

        binding.viewModel = viewModel
    }

    private fun startIconAnimation() = ObjectAnimator.ofFloat(binding.imvIcon, "rotationY", 360f).apply {
        startDelay = 300
        duration = 2500
        interpolator = LinearInterpolator()
        repeatCount = INFINITE
        start()
    }

    private fun navigateToListScreen() =
        navigateUsingAction(R.id.nav_splash_to_release_list,
            FragmentNavigatorExtras(binding.imvIcon to "love_symbol")
        )

    override fun onErrorMessageButtonClick(err: NetworkErr, dialog: DialogInterface) {
        super.onErrorMessageButtonClick(err, dialog)
        when(err) {
            NetworkErr.onNetworkError() -> finish()
            else -> dialog.dismiss()
        }
    }

    private fun finish() = requireActivity().finishAffinity()
}