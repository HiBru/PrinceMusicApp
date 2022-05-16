package de.hicedevelopments.princemusicapp.data.repository

import de.hicedevelopments.princemusicapp.data.local.dao.ReleaseDao
import de.hicedevelopments.princemusicapp.data.model.*
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.PAGE
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.PER_PAGE
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_ARTIST
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_COUNTRY
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_FORMAT
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
    single { Repo(get(), get()) }
}

class Repo(
    private val api: DiscogsApi,
    private val releaseDao: ReleaseDao
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
    }.onEach { releases ->
        releases?.releases?.let {
            //insertReleases(it)
        }
    }.flowOn(Dispatchers.IO)

    fun getAlbumList(perPage: Int, page: Int): Flow<Search?> = flow {
        api.search(hashMapOf(
            SEARCH_ARTIST to "Prince",
            SEARCH_FORMAT to "album",
            SEARCH_COUNTRY to "Germany",
            SEARCH_SORT to "year",
            SEARCH_SORT_ORDER to "asc",
            PER_PAGE to "$perPage",
            PAGE to "$page"
        )).let { response ->
            with(NetworkWrapper(response)) {
                when (state) {
                    Success -> emit(model)
                    else -> throw NetworkException(state)
                }
            }
        }
    }.onEach {
        //insertResults(it)
    }.flowOn(Dispatchers.IO)

    fun getRelease(id: String): Flow<Master?> = flow {
        with(NetworkWrapper(api.release(id))) {
            when(state) {
                Success -> emit(model)
                else -> throw NetworkException(state)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getArtistInfo(id: String): Flow<Artist?> = flow {
        with(NetworkWrapper(api.getArtistsInfo(id))) {
            when(state) {
                Success -> emit(model)
                else -> throw NetworkException(state)
            }
        }
    }.flowOn(Dispatchers.IO)

    /**
     * RESULT DAO
     */
    private fun insertReleases(items: List<Release>?) = releaseDao.insertAll(items)
    fun getResults(): Flow<List<Release>?> = releaseDao.getReleases()
}