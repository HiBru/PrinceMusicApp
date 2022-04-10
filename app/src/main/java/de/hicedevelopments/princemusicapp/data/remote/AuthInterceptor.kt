package de.hicedevelopments.princemusicapp.data.remote

import de.hicedevelopments.princemusicapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authHeader = "Discogs key=%s, secret=%s".format(BuildConfig.KEY, BuildConfig.SECRET)
        val original = chain.request()
        val request = original.newBuilder()
            .header("User-Agent", BuildConfig.USER_AGENT)
            .header("Authorization", authHeader)
            //.header("Authorization", BuildConfig.AUTH_TOKEN)
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }
}