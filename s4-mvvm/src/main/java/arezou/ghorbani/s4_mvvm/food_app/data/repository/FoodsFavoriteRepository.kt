package arezou.ghorbani.s4_mvvm.food_app.data.repository

import arezou.ghorbani.s4_mvvm.food_app.data.database.FoodDao
import javax.inject.Inject

class FoodsFavoriteRepository @Inject constructor(private val dao: FoodDao) {
    fun foodsList() = dao.getAllFoods()
}