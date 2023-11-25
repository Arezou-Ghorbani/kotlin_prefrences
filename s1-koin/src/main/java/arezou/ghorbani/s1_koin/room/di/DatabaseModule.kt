package arezou.ghorbani.s1_koin.room.di

import arezou.ghorbani.s1_koin.room.db.provideDao
import arezou.ghorbani.s1_koin.room.db.provideDatabase
import arezou.ghorbani.s1_koin.room.repository.RoomRepository
import arezou.ghorbani.s1_koin.room.viewmodel.RoomViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }

    factory { RoomRepository(get()) }

    viewModel { RoomViewModel(get()) }
}