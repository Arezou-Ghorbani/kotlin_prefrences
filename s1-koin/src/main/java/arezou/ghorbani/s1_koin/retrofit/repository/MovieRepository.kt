package arezou.ghorbani.s1_koin.retrofit.repository

import arezou.ghorbani.s1_koin.retrofit.api.ApiServices

class MovieRepository(private val api: ApiServices) {

    suspend fun showMovies() = api.allMovies()
}