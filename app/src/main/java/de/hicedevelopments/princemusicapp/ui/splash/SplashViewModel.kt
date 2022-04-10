package de.hicedevelopments.princemusicapp.ui.splash

import androidx.lifecycle.MutableLiveData
import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.data.model.Artist
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.model.Result
import de.hicedevelopments.princemusicapp.data.repository.Repo
import kotlinx.coroutines.flow.*

class SplashViewModel(
    private val repo: Repo
) : BaseViewModel() {

    val results = MutableLiveData<List<Result>>()
    val release = MutableLiveData<Release>()
    val artist = MutableLiveData<Artist>()

    init {
        getAlbumList()
    }

    private fun getAlbumList() = asyncWithLoadingState {
        repo.getAlbumList().collect { items ->
                results.postValue(items)
            }
    }

    fun getRelease(id: String) = asyncWithLoadingState {
        repo.getRelease("$id").collect { item ->
                release.postValue(item)
            }
    }

    fun getArtistInfo(id: String) = asyncWithLoadingState {
        repo.getArtistInfo("$id").collect { item ->
                artist.postValue(item)
            }
    }
}