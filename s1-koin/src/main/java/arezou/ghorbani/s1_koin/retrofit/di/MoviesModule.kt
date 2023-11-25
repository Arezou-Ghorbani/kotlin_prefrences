package arezou.ghorbani.s1_koin.retrofit.di

import arezou.ghorbani.s1_koin.retrofit.repository.MovieRepository
import arezou.ghorbani.s1_koin.retrofit.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {
    single { MovieRepository(get()) }
    viewModel { MoviesViewModel(get()) }
}