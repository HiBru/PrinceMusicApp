package de.hicedevelopments.princemusicapp.di.module

import android.content.Context
import androidx.room.Room
import de.hicedevelopments.princemusicapp.data.local.PrinceDB
import de.hicedevelopments.princemusicapp.data.local.dao.ArtistInfoDao
import de.hicedevelopments.princemusicapp.data.local.dao.ReleaseDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideResultDao(get()) }
    single { provideArtistInfoDao(get()) }
    single { providePrinceDB(get()) }
}

fun provideResultDao(db: PrinceDB): ReleaseDao {
    return db.releaseDao()
}

fun provideArtistInfoDao(db: PrinceDB): ArtistInfoDao {
    return db.artistInfoDao()
}

fun providePrinceDB(context: Context): PrinceDB {
    return Room.databaseBuilder(context, PrinceDB::class.java, "prince_db")
        .fallbackToDestructiveMigration()
        .build()
}