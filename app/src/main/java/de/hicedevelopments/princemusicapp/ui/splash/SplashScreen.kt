package de.hicedevelopments.princemusicapp.ui.splash

import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingAction
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingDirections
import de.hicedevelopments.princemusicapp.app.extension.showToast
import de.hicedevelopments.princemusicapp.data.model.Result
import de.hicedevelopments.princemusicapp.databinding.ViewSplashBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreen : ResourceFragment<ViewSplashBinding>() {

    override val layoutId = R.layout.view_splash
    override val viewModel: SplashViewModel by viewModel()

    override fun bindViewModel(binding: ViewSplashBinding) {
        viewModel.results.observe(viewLifecycleOwner) {
            showToast("results completed")
            navigateToListScreen()
        }

        binding.viewModel = viewModel
    }

    private fun navigateToListScreen() = navigateUsingAction(R.id.nav_splash_to_release_list)
}