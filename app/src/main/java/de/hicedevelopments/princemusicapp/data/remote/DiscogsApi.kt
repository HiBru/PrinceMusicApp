package de.hicedevelopments.princemusicapp.data.remote

import de.hicedevelopments.princemusicapp.data.model.Artist
import de.hicedevelopments.princemusicapp.data.model.Master
import de.hicedevelopments.princemusicapp.data.model.Releases
import de.hicedevelopments.princemusicapp.data.model.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface DiscogsApi {

    companion object {
        const val SEARCH_ARTIST = "artist"
        const val SEARCH_COUNTRY = "country"
        const val SEARCH_FORMAT = "format"
        const val SEARCH_TYPE = "type"
        const val SEARCH_SUBTYPE = "subtype"
        const val SEARCH_SORT = "sort"
        const val SEARCH_SORT_ORDER = "sort_order"
        const val PER_PAGE = "per_page"
        const val PAGE = "page"
    }

    @GET("artists/28795/releases")
    suspend fun releases(@QueryMap options: Map<String, String>): Response<Releases>

    @GET("database/search")
    suspend fun search(@QueryMap options: Map<String, String>): Response<Search>

    @GET("releases/{release_id}")
    suspend fun release(@Path("release_id") releaseId: String): Response<Master>

    @GET("masters/{master_id}")
    suspend fun master(@Path("master_id") masterId: String): Response<Master>

    @GET("artists/28795")
    suspend fun getArtistsInfo(@Path("artist_id") artistId: String): Response<Artist>
}