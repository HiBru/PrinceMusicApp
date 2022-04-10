package de.hicedevelopments.princemusicapp.ui.releasedetail

import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingAction
import de.hicedevelopments.princemusicapp.databinding.ViewReleaseDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseDetailScreen : ResourceFragment<ViewReleaseDetailBinding>() {

    override val layoutId = R.layout.view_release_detail
    override val viewModel: ReleaseDetailViewModel by viewModel()

    override fun bindViewModel(binding: ViewReleaseDetailBinding) {
        binding.viewModel = viewModel
        binding.tvText.setOnClickListener { navigateUsingAction(R.id.nav_release_detail_to_artist_detail) }
    }
}