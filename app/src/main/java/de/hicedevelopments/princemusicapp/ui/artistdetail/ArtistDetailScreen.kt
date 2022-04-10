package de.hicedevelopments.princemusicapp.ui.artistdetail

import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.ResourceFragment
import de.hicedevelopments.princemusicapp.databinding.ViewArtistDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtistDetailScreen : ResourceFragment<ViewArtistDetailBinding>() {

    override val layoutId = R.layout.view_artist_detail
    override val viewModel:ArtistDetailViewModel by viewModel()

    override fun bindViewModel(binding: ViewArtistDetailBinding) {
        binding.viewModel = viewModel
    }
}