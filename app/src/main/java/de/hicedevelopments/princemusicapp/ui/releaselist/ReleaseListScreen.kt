package de.hicedevelopments.princemusicapp.ui.releaselist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.transition.TransitionInflater
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingDirections
import de.hicedevelopments.princemusicapp.data.model.AboutType
import de.hicedevelopments.princemusicapp.databinding.ViewReleaseListBinding
import de.hicedevelopments.princemusicapp.ui.releaselist.ReleaseListViewModel.ReleaseListEvent.SCROLL_TO_TOP
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListItem
import de.hicedevelopments.princemusicapp.ui.releaselist.items.ReleaseListListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseListScreen : ResourceFragment<ViewReleaseListBinding>(), ReleaseListListener, View.OnScrollChangeListener, SwipeRefreshLayout.OnRefreshListener {

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
        postponeEnterTransition()
        binding.releases.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    override fun showLoading(isLoading: Boolean) {
        binding.refreshReleases.isRefreshing = isLoading
    }

    override fun onRefresh() {
        viewModel.refresh()
    }

    override fun onScrollChange(view: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (scrollY < oldScrollY) binding.fbScrollToTop.show()
        if ((binding.releases.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0) binding.fbScrollToTop.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.about_prince -> {
                navigateToAbout()
                true
            }
            R.id.about_me -> {
                navigateToAboutMe()
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
        binding.refreshReleases.setOnRefreshListener(this)
    }

    override fun onReleaseItemClick(position: Int, item: ReleaseListItem) {
        binding.releases.layoutManager
            ?.findViewByPosition(position)
            ?.findViewById<AppCompatImageView>(R.id.imv_thumb)
            ?.let { imageView ->
                navigateToDetailScreen(imageView, item)
            }
    }

    private fun handleEvent(event: ReleaseListViewModel.ReleaseListEvent) {
        when(event) {
            SCROLL_TO_TOP -> scrollToTop()
        }
    }

    private fun scrollToTop() = binding.releases.scrollToPosition(0)

    private fun navigateToDetailScreen(imageView: AppCompatImageView, item: ReleaseListItem) = navigateUsingDirections(
        ReleaseListScreenDirections.navReleaseListToReleaseDetail(item),
        FragmentNavigator.Extras.Builder().addSharedElement(imageView, imageView.transitionName).build()
    )
    private fun navigateToAbout() = navigateUsingDirections(ReleaseListScreenDirections.navReleaseListToAbout(AboutType.PRINCE))
    private fun navigateToAboutMe() = navigateUsingDirections(ReleaseListScreenDirections.navReleaseListToAbout(AboutType.ME))
}