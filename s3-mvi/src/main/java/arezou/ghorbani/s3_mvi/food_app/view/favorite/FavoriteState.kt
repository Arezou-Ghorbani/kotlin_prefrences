package arezou.ghorbani.s3_mvi.food_app.view.favorite

import arezou.ghorbani.s3_mvi.food_app.data.database.FoodEntity

sealed class FavoriteState {
    object Empty : FavoriteState()
    data class LoadFavorites(val data: MutableList<FoodEntity>) : FavoriteState()
}
