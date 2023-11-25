package arezou.ghorbani.s1_hilt.retrofit.repository

import arezou.ghorbani.s1_hilt.retrofit.api.ApiServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(private val api: ApiServices) {
    fun getAllMovies() = api.moviesList()
}