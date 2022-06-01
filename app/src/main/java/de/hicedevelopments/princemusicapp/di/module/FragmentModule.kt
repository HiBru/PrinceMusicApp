package de.hicedevelopments.princemusicapp.di.module

import de.hicedevelopments.princemusicapp.ui.artistdetail.ArtistDetailScreen
import de.hicedevelopments.princemusicapp.ui.releasedetail.ReleaseDetailScreen
import de.hicedevelopments.princemusicapp.ui.releasedetail.imageslider.ImageSliderDialog
import de.hicedevelopments.princemusicapp.ui.releaselist.ReleaseListScreen
import de.hicedevelopments.princemusicapp.ui.splash.SplashScreen
import org.koin.dsl.module

val fragmentModule = module {
    factory { SplashScreen() }
    factory { ReleaseListScreen() }
    factory { ReleaseDetailScreen() }
    factory { ArtistDetailScreen() }
    factory { ImageSliderDialog() }
}