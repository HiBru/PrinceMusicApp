package de.hicedevelopments.princemusicapp.di.module

import de.hicedevelopments.princemusicapp.BuildConfig
import de.hicedevelopments.princemusicapp.data.remote.AuthInterceptor
import de.hicedevelopments.princemusicapp.data.remote.DiscogsApi
import de.hicedevelopments.princemusicapp.data.remote.GsonProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { AuthInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideDiscogsApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val gsonConverter = GsonConverterFactory.create(GsonProvider().gsonInstance)

    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverter)
        .build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    val logging = HttpLoggingInterceptor()

    if (BuildConfig.DEBUG) {
        logging.level = HttpLoggingInterceptor.Level.BODY
    } else {
        logging.level = HttpLoggingInterceptor.Level.BASIC
    }

    return OkHttpClient().newBuilder()
        .callTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor(authInterceptor)
        .build()
}

fun provideDiscogsApi(retrofit: Retrofit): DiscogsApi = retrofit.create(DiscogsApi::class.java)