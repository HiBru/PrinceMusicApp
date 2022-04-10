package de.hicedevelopments.princemusicapp.di.module

import de.hicedevelopments.princemusicapp.ui.artistdetail.ArtistDetailViewModel
import de.hicedevelopments.princemusicapp.ui.releasedetail.ReleaseDetailViewModel
import de.hicedevelopments.princemusicapp.ui.releaselist.ReleaseListViewModel
import de.hicedevelopments.princemusicapp.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ReleaseListViewModel(get()) }
    viewModel { ReleaseDetailViewModel() }
    viewModel { ArtistDetailViewModel() }
}