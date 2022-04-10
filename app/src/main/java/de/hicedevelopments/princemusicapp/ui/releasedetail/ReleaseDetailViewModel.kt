package de.hicedevelopments.princemusicapp.ui.releasedetail

import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.repository.Repo
import kotlinx.coroutines.flow.collect

class ReleaseDetailViewModel(
    private val repo: Repo
) : BaseViewModel() {

    val release = SingleLiveEvent<Release>()

    fun loadRelease(id: String) = asyncWithLoadingState {
        repo.getRelease(id).collect { item ->
            release.postValue(item)
        }
    }
}