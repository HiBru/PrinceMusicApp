package de.hicedevelopments.princemusicapp.ui.releasedetail

import androidx.navigation.fragment.navArgs
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.showToast
import de.hicedevelopments.princemusicapp.databinding.ViewReleaseDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseDetailScreen : ResourceFragment<ViewReleaseDetailBinding>() {

    override val layoutId = R.layout.view_release_detail
    override val viewModel: ReleaseDetailViewModel by viewModel()

    private val args: ReleaseDetailScreenArgs by navArgs()

    override fun bindViewModel(binding: ViewReleaseDetailBinding) {
        viewModel.release.observe(viewLifecycleOwner) {
            showToast("release completed")
        }
        binding.viewModel = viewModel

        viewModel.loadRelease(args.releaseId)
    }
}