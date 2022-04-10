package de.hicedevelopments.princemusicapp.ui.releaselist

import androidx.lifecycle.viewModelScope
import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.Result
import de.hicedevelopments.princemusicapp.data.repository.Repo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReleaseListViewModel(
    private val repo: Repo
) : BaseViewModel() {

    val results = SingleLiveEvent<List<Result>>()

    init {
        getResults()
    }

    private fun getResults() = viewModelScope.launch {
        repo.getResults().collect { items ->
            results.postValue(items)
        }
    }
}