package de.hicedevelopments.princemusicapp.ui.splash

import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.repository.Repo
import de.hicedevelopments.princemusicapp.ui.releaselist.PAGINATION_PAGE_SIZE
import kotlinx.coroutines.flow.collect

class SplashViewModel(
    private val repo: Repo
) : BaseViewModel() {

    val results = SingleLiveEvent<List<Release>>()

    init {
        getAlbumList()
    }

    private fun getAlbumList() = asyncWithLoadingState(withLoadingState = false) {
        repo.getReleases(PAGINATION_PAGE_SIZE, 1).collect { response ->
                results.postValue(response?.releases ?: listOf())
            }
    }
}