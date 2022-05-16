package de.hicedevelopments.princemusicapp.ui.releaselist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingDirections
import de.hicedevelopments.princemusicapp.app.extension.showToast
import de.hicedevelopments.princemusicapp.databinding.ViewReleaseListBinding
import de.hicedevelopments.princemusicapp.ui.releaselist.ReleaseListViewModel.ReleaseListEvent.SCROLL_TO_TOP
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListItem
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseListScreen : ResourceFragment<ViewReleaseListBinding>(), ReleaseListListener, View.OnScrollChangeListener {

    override var optionsMenu: Int? = R.menu.about_menu
    override val layoutId = R.layout.view_release_list
    override val viewModel:ReleaseListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    override fun onScrollChange(view: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (scrollY < oldScrollY) binding.fbScrollToTop.show()
        if ((binding.releases.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0) binding.fbScrollToTop.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.about_prince -> {
                navigateToArtistDetail()
                true
            }
            R.id.about_me -> {
                showToast("about me")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun bindViewModel(binding: ViewReleaseListBinding) {
        viewModel.itemClickListener = this
        viewModel.eventStream.observe(viewLifecycleOwner) { event -> handleEvent(event) }
        binding.viewModel = viewModel
        binding.releases.setOnScrollChangeListener(this)
    }

    override fun onReleaseItemClick(item: ReleaseListItem) {
        navigateToDetailScreen(item.id)
    }

    private fun handleEvent(event: ReleaseListViewModel.ReleaseListEvent) {
        when(event) {
            SCROLL_TO_TOP -> scrollToTop()
        }
    }

    private fun scrollToTop() = binding.releases.scrollToPosition(0)

    private fun navigateToDetailScreen(id: Int) = navigateUsingDirections(ReleaseListScreenDirections.navReleaseListToReleaseDetail(id.toString()))
    private fun navigateToArtistDetail() = navigateUsingDirections(ReleaseListScreenDirections.navReleaseListToArtistDetail())
}