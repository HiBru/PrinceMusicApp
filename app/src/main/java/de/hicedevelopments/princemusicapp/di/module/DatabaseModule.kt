package de.hicedevelopments.princemusicapp.di.module

import android.content.Context
import androidx.room.Room
import de.hicedevelopments.princemusicapp.data.local.PrinceDB
import de.hicedevelopments.princemusicapp.data.local.dao.ResultDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideResultDao(get()) }
    single { providePrinceDB(get()) }
}

fun provideResultDao(db: PrinceDB): ResultDao {
    return db.resultDao()
}

fun providePrinceDB(context: Context): PrinceDB {
    return Room.databaseBuilder(context, PrinceDB::class.java, "prince_db")
        .fallbackToDestructiveMigration()
        .build()
}