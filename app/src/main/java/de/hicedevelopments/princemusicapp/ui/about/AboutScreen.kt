package de.hicedevelopments.princemusicapp.ui.about

import androidx.navigation.fragment.navArgs
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.app.extension.navigateUsingDirections
import de.hicedevelopments.princemusicapp.app.extension.openWebsite
import de.hicedevelopments.princemusicapp.data.model.WebLink
import de.hicedevelopments.princemusicapp.data.model.WebLinkListener
import de.hicedevelopments.princemusicapp.databinding.ViewAboutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutScreen : ResourceFragment<ViewAboutBinding>(), WebLinkListener {

    override val layoutId = R.layout.view_about
    override val viewModel:AboutViewModel by viewModel()

    private val args: AboutScreenArgs by navArgs()

    override fun bindViewModel(binding: ViewAboutBinding) {
        viewModel.eventStream.observe(viewLifecycleOwner) { showImageSlider() }
        viewModel.webLinkListener = this

        binding.viewModel = viewModel

        viewModel.loadAboutItem(args.type)
    }

    private fun showImageSlider() {
        viewModel.aboutModel.value?.images?.let { images ->
            if (images.count() > 1) navigateUsingDirections(
                AboutScreenDirections.navArtistDetailToImageSliderDialog(images.toTypedArray())
            )
        }
    }

    override fun onWebLinkClick(link: WebLink) {
        openWebsite(link.link)
    }
}