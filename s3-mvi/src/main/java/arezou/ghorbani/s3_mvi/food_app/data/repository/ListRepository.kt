package arezou.ghorbani.s3_mvi.food_app.data.repository

import arezou.ghorbani.s3_mvi.food_app.data.server.ApiServices
import javax.inject.Inject

class ListRepository @Inject constructor(private val api: ApiServices) {
    suspend fun randomFood() = api.foodRandom()
    suspend fun categoriesList() = api.categoriesList()
    suspend fun foodsList(letter: String) = api.foodsList(letter)
    suspend fun searchFood(letter: String) = api.searchFood(letter)
    suspend fun foodByCategory(letter: String) = api.foodsByCategory(letter)
}