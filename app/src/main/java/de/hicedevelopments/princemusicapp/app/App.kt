package de.hicedevelopments.princemusicapp.app

import android.app.Application
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import de.hicedevelopments.princemusicapp.BuildConfig
import de.hicedevelopments.princemusicapp.common.GlideApp
import de.hicedevelopments.princemusicapp.data.repository.repoModule
import de.hicedevelopments.princemusicapp.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    private val appModules = listOf(
        connectionModule,
        databaseModule,
        fragmentModule,
        networkModule,
        repoModule,
        viewModelModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if(BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            fragmentFactory()
            modules(appModules)
        }
        initGlide()
    }

    private fun initGlide() = GlideApp.get(this)
}