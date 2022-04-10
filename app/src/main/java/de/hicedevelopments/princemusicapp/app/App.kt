package de.hicedevelopments.princemusicapp.app

import android.app.Application
import de.hicedevelopments.princemusicapp.BuildConfig
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
    }
}