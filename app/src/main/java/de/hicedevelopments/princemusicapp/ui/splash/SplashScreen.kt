package de.hicedevelopments.princemusicapp.ui.splash

import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingAction
import de.hicedevelopments.princemusicapp.app.extension.showToast
import de.hicedevelopments.princemusicapp.databinding.ViewSplashBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreen : ResourceFragment<ViewSplashBinding>() {

    override val layoutId = R.layout.view_splash
    override val viewModel: SplashViewModel by viewModel()

    override fun bindViewModel(binding: ViewSplashBinding) {
        viewModel.results.observe(viewLifecycleOwner) { items ->
            showToast("results completed")
            items?.firstOrNull()?.id?.let {
                viewModel.getRelease("$it")
            }
        }
        viewModel.release.observe(viewLifecycleOwner) { item ->
            showToast("release completed")
            item?.artists?.firstOrNull()?.id?.let {
                viewModel.getArtistInfo("$it")
            }
        }
        viewModel.artist.observe(viewLifecycleOwner) {
            showToast("artist completed")
        }

        binding.viewModel = viewModel
        binding.tvText.setOnClickListener { navigateUsingAction(R.id.nav_splash_to_release_list) }
    }
}