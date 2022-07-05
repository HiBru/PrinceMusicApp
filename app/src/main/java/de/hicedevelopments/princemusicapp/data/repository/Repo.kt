package de.hicedevelopments.princemusicapp.data.repository

import android.content.Context
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.data.local.dao.AboutDao
import de.hicedevelopments.princemusicapp.data.local.dao.ReleaseDao
import de.hicedevelopments.princemusicapp.data.model.*
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.PAGE
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.PER_PAGE
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_SORT
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_SORT_ORDER
import de.hicedevelopments.princemusicapp.data.remote.NetworkException
import de.hicedevelopments.princemusicapp.data.remote.NetworkWrapper
import de.hicedevelopments.princemusicapp.data.remote.NetworkWrapper.State.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import org.koin.dsl.module

val repoModule = module {
    single { Repo(get(), get(), get(), get()) }
}

class Repo(
    private val context: Context,
    private val api: DiscogsApi,
    private val releaseDao: ReleaseDao,
    private val aboutDao: AboutDao
) {

    fun getReleases(perPage: Int, page: Int): Flow<Releases?> = flow {
        api.releases(hashMapOf(
            SEARCH_SORT to "year",
            SEARCH_SORT_ORDER to "asc",
            PER_PAGE to "$perPage",
            PAGE to "$page"
        )).let { response ->
            with(NetworkWrapper(response)) {
                when(state) {
                    Success -> emit(model)
                    else -> throw NetworkException(state)
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getRelease(id: String): Flow<Master?> = flow {
        with(NetworkWrapper(api.release(id))) {
            when(state) {
                Success -> emit(model)
                else -> throw NetworkException(state)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getMaster(id: String): Flow<Master?> = flow {
        with(NetworkWrapper(api.master(id))) {
            when(state) {
                Success -> emit(model)
                else -> throw NetworkException(state)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getArtistInfo(): Flow<ArtistInfo?> = flow {
        with(NetworkWrapper(api.getArtistsInfo())) {
            when(state) {
                Success -> {
                    emit(model)
                }
                else -> throw NetworkException(state)
            }
        }
    }.onEach { info ->
        info?.let { data ->
            insertAboutItem(
                About(
                type = AboutType.PRINCE,
                title = context.getString(R.string.about_prince_title),
                realName = data.realName,
                nameVariations = data.nameVariations,
                profile = context.getString(R.string.about_artist),
                links = data.urls?.map { WebLink(text = null, link = it) },
                images = data.images)
            )
        }
    }.flowOn(Dispatchers.IO)

    /**
     * RELEASE DAO
     */
    private fun insertReleases(items: List<Release>?) = releaseDao.insertAll(items)
    fun getResults(): Flow<List<Release>?> = releaseDao.getReleases()

    /**
     * ABOUT DAO
     */
    suspend fun insertAboutItem(item: About) = aboutDao.insertAboutItem(item)
    fun getAboutItem(type: AboutType): Flow<About?> = aboutDao.getAboutItem(type)
}