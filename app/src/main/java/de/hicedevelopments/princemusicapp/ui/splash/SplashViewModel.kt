package de.hicedevelopments.princemusicapp.ui.splash

import android.content.Context
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.app.BaseViewModel
import de.hicedevelopments.princemusicapp.common.SingleLiveEvent
import de.hicedevelopments.princemusicapp.data.model.About
import de.hicedevelopments.princemusicapp.data.model.AboutType
import de.hicedevelopments.princemusicapp.data.model.ArtistInfo
import de.hicedevelopments.princemusicapp.data.model.Image
import de.hicedevelopments.princemusicapp.data.repository.Repo
import kotlinx.coroutines.flow.*

class SplashViewModel(
    private val repo: Repo
) : BaseViewModel() {

    val result = SingleLiveEvent<ArtistInfo?>()

    fun getData(context: Context) = asyncWithLoadingState(withLoadingState = false) {
        loadArtistInfo().combine(loadAboutMe()) { info, aboutMe ->
            aboutMe.let { about ->
                about?.let { result.postValue(info) } ?: insertAboutMe(context, info)
            }
        }.collect()
    }

    private fun insertAboutMe(context: Context, artistInfo: ArtistInfo?) = asyncWithLoadingState(withLoadingState = false) {
        with(About.getAboutMeItem(context)) {
            flow {
                emit(repo.insertAboutItem(this@with))
            }.collect {
                result.postValue(artistInfo)
            }
        }
    }

    private fun loadArtistInfo(): Flow<ArtistInfo?> = repo.getArtistInfo()
    private fun loadAboutMe(): Flow<About?> = repo.getAboutItem(AboutType.ME)
}