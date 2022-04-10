package de.hicedevelopments.princemusicapp.di.module

import de.hicedevelopments.princemusicapp.util.ConnectionHelper
import org.koin.dsl.module

val connectionModule = module {
    factory { ConnectionHelper(get()) }
}