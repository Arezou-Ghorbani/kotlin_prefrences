package arezou.ghorbani.s1_paging.di

import arezou.ghorbani.s1_paging.adapter.MoviesAdapterKoin
import arezou.ghorbani.s1_paging.api.*
import arezou.ghorbani.s1_paging.repository.MoviesRepositoryKoin
import arezou.ghorbani.s1_paging.viewmodel.MoviesViewModelKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {
    single { baseUrl }
    single { networkTime }
    single { provideGson() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get(), get()) }

    single { MoviesRepositoryKoin(get()) }
    viewModel { MoviesViewModelKoin(get()) }
    single { MoviesAdapterKoin() }
}