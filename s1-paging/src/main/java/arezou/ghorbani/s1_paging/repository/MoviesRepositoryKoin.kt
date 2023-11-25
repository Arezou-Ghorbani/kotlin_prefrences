package arezou.ghorbani.s1_paging.repository

import arezou.ghorbani.s1_paging.api.ApiServices

class MoviesRepositoryKoin (private val api: ApiServices) {
    suspend fun getAllMovies(page: Int) = api.getAllMovies(page)
}