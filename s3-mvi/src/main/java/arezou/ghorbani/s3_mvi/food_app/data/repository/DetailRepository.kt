package arezou.ghorbani.s3_mvi.food_app.data.repository

import arezou.ghorbani.s3_mvi.food_app.data.database.FoodDao
import arezou.ghorbani.s3_mvi.food_app.data.database.FoodEntity
import arezou.ghorbani.s3_mvi.food_app.data.server.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices, private val dao: FoodDao) {
    suspend fun detailFood(id: Int) = api.foodDetail(id)
    suspend fun saveFood(entity: FoodEntity) = dao.saveFood(entity)
    suspend fun deleteFood(entity: FoodEntity) = dao.deleteFood(entity)
    fun existsFood(id: Int) = dao.existsFood(id)
}