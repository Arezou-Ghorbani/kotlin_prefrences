package arezou.ghorbani.s3_mvi.food_app.data.repository

import arezou.ghorbani.s3_mvi.food_app.data.database.FoodDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: FoodDao) {
    fun foodsList() = dao.getAllFoods()
}