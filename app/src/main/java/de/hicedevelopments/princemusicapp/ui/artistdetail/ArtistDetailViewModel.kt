package de.hicedevelopments.princemusicapp.ui.artistdetail

import androidx.lifecycle.MutableLiveData
import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.ArtistInfo
import de.hicedevelopments.princemusicapp.data.repository.Repo
import kotlinx.coroutines.flow.collect

class ArtistDetailViewModel(
    private val repo: Repo
) : BaseViewModel() {

    enum class ArtistDetailEvent {
        IMAGE_CLICK
    }

    init {
        loadArtistDetails()
    }

    val eventStream = SingleLiveEvent<ArtistDetailEvent>()
    val artistInfo = MutableLiveData<ArtistInfo>()
    val artistUrls = MutableLiveData<String>()
    val artistNameVariations = MutableLiveData<String>()

    private fun loadArtistDetails() = asyncWithLoadingState {
        repo.getArtistInfo().collect { data ->
            artistInfo.postValue(data)
            artistUrls.postValue(data?.urls?.joinToString("\n"))
            artistNameVariations.postValue(data?.nameVariations?.joinToString("\n"))
        }
    }

    fun onEvent(event: ArtistDetailEvent) = eventStream.postValue(event)
}