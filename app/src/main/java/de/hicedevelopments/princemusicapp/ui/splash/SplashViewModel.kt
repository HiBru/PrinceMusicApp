package de.hicedevelopments.princemusicapp.ui.splash

import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.Result
import de.hicedevelopments.princemusicapp.data.repository.Repo
import kotlinx.coroutines.flow.collect

class SplashViewModel(
    private val repo: Repo
) : BaseViewModel() {

    val results = SingleLiveEvent<List<Result>>()

    init {
        getAlbumList()
    }

    private fun getAlbumList() = asyncWithLoadingState {
        repo.getAlbumList().collect { items ->
                isLoading.postValue(false)
                results.postValue(items)
            }
    }
}