package de.hicedevelopments.princemusicapp.ui.releaselist

import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingDirections
import de.hicedevelopments.princemusicapp.app.extension.showToast
import de.hicedevelopments.princemusicapp.databinding.ViewReleaseListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseListScreen : ResourceFragment<ViewReleaseListBinding>() {

    override val layoutId = R.layout.view_release_list
    override val viewModel:ReleaseListViewModel by viewModel()

    override fun bindViewModel(binding: ViewReleaseListBinding) {
        viewModel.results.observe(viewLifecycleOwner) { items ->
            showToast("local results completed")
            items.firstOrNull()?.id?.let { id ->
                navigateToDetailScreen(id)
            }
        }

        binding.viewModel = viewModel
    }

    private fun navigateToDetailScreen(id: Int) = navigateUsingDirections(ReleaseListScreenDirections.navReleaseListToReleaseDetail(id.toString()))
}