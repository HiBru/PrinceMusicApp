package de.hicedevelopments.princemusicapp.data.repository

import de.hicedevelopments.princemusicapp.data.model.Artist
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.model.Result
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_ARTIST
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_COUNTRY
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_FORMAT
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_SORT
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_SORT_ORDER
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi.Companion.SEARCH_TYPE
import de.hicedevelopments.princemusicapp.data.remote.NetworkException
import de.hicedevelopments.princemusicapp.data.remote.NetworkWrapper
import de.hicedevelopments.princemusicapp.data.remote.NetworkWrapper.State.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.dsl.module

val repoModule = module {
    single { Repo(get()) }
}

class Repo(
    private val api: DiscogsApi
) {

    fun getAlbumList(): Flow<List<Result>?> = flow {
        api.search(hashMapOf(
            SEARCH_ARTIST to "Prince",
            SEARCH_TYPE to "album",
            SEARCH_FORMAT to "album",
            SEARCH_COUNTRY to "Germany",
            SEARCH_SORT to "year",
            SEARCH_SORT_ORDER to "asc"
        )).let { response ->
            with(NetworkWrapper(response)) {
                when (state) {
                    Success -> emit(model?.results)
                    else -> throw NetworkException(state)
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getRelease(id: String): Flow<Release?> = flow {
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
}