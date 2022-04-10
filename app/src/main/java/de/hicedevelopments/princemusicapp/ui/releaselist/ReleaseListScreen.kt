package de.hicedevelopments.princemusicapp.ui.releaselist

import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingAction
import de.hicedevelopments.princemusicapp.app.extension.showToast
import de.hicedevelopments.princemusicapp.databinding.ViewReleaseListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseListScreen : ResourceFragment<ViewReleaseListBinding>() {

    override val layoutId = R.layout.view_release_list
    override val viewModel:ReleaseListViewModel by viewModel()

    override fun bindViewModel(binding: ViewReleaseListBinding) {
        viewModel.results.observe(viewLifecycleOwner) {
            showToast("local results completed")
            //navigateToDetailScreen()
        }

        binding.viewModel = viewModel
    }

    private fun navigateToDetailScreen() = navigateUsingAction(R.id.nav_release_list_to_release_detail)
}