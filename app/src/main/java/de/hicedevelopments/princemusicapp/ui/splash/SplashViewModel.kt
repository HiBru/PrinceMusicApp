package de.hicedevelopments.princemusicapp.ui.splash

import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.ArtistInfo
import de.hicedevelopments.princemusicapp.data.repository.Repo
import kotlinx.coroutines.flow.collect

class SplashViewModel(
    private val repo: Repo
) : BaseViewModel() {

    val result = SingleLiveEvent<ArtistInfo?>()

    init {
        getArtistInfo()
    }

    private fun getArtistInfo() = asyncWithLoadingState(withLoadingState = false) {
        repo.getArtistInfo().collect { response ->
                result.postValue(response)
            }
    }
}