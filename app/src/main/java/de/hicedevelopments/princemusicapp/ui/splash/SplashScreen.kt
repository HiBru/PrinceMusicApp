package de.hicedevelopments.princemusicapp.ui.splash

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.content.DialogInterface
import android.view.animation.LinearInterpolator
import androidx.navigation.fragment.FragmentNavigator
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingAction
import de.hicedevelopments.princemusicapp.data.remote.NetworkErr
import de.hicedevelopments.princemusicapp.data.remote.NetworkWrapper.State.NetworkError
import de.hicedevelopments.princemusicapp.databinding.ViewSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreen : ResourceFragment<ViewSplashBinding>() {

    override val layoutId = R.layout.view_splash
    override val viewModel: SplashViewModel by viewModel()

    private val animation by lazy {
        ObjectAnimator.ofFloat(binding.imvIcon, "rotationY", 360f).apply {
            startDelay = 300
            duration = 2500
            interpolator = LinearInterpolator()
            repeatCount = INFINITE
        }
    }

    override fun bindViewModel(binding: ViewSplashBinding) {
        //animation.start()
        viewModel.result.observe(viewLifecycleOwner) {
            navigateToListScreen()
        }

        binding.viewModel = viewModel

        viewModel.getData(requireContext())
    }

    private fun navigateToListScreen() =
        navigateUsingAction(R.id.nav_splash_to_release_list,
            FragmentNavigator.Extras.Builder().addSharedElement(binding.imvIcon, binding.imvIcon.transitionName).build()
        )

    override fun onErrorMessageButtonClick(err: NetworkErr, dialog: DialogInterface) {
        when(err.errState) {
            NetworkError -> finish()
            else -> dialog.dismiss()
        }
    }

    private fun finish() = requireActivity().finishAffinity()
}