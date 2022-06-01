package de.hicedevelopments.princemusicapp.ui.releasedetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.navArgs
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionListenerAdapter
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingDirections
import de.hicedevelopments.princemusicapp.app.extension.openWebsite
import de.hicedevelopments.princemusicapp.data.model.VideoLink
import de.hicedevelopments.princemusicapp.databinding.ViewReleaseDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseDetailScreen : ResourceFragment<ViewReleaseDetailBinding>(), VideoLinkListener {

    override val layoutId = R.layout.view_release_detail
    override val viewModel: ReleaseDetailViewModel by viewModel()

    private val args: ReleaseDetailScreenArgs by navArgs()
    private val releaseItem by lazy { args.releaseItem }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
            .apply {
                addListener(object: TransitionListenerAdapter() {
                    override fun onTransitionEnd(transition: Transition) {
                        super.onTransitionEnd(transition)
                        viewModel.loadDetails(releaseItem.type, "${releaseItem.id}")
                        removeListener(this)
                    }
                })
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    override fun bindViewModel(binding: ViewReleaseDetailBinding) {
        viewModel.eventStream.observe(viewLifecycleOwner) { showImageSlider() }
        binding.transitionName = "${releaseItem.id}"
        binding.thumb = releaseItem.thumb
        binding.viewModel = viewModel

        viewModel.videoLinkListener = this
    }

    override fun onVideoLinkClick(link: VideoLink) {
        openWebsite(link.uri)
    }

    private fun showImageSlider() {
        viewModel.detailItem.value?.images?.let { images ->
            if (images.count() > 1) navigateUsingDirections(
                ReleaseDetailScreenDirections.navReleaseDetailToImageSliderDialog(images.toTypedArray()),
                FragmentNavigator.Extras.Builder().addSharedElement(binding.header, binding.header.transitionName).build()
            )
        }
    }
}