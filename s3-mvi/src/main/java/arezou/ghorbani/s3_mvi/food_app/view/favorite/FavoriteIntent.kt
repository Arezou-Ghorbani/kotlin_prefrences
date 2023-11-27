package arezou.ghorbani.s3_mvi.food_app.view.favorite

sealed class FavoriteIntent {
    object LoadFavorites : FavoriteIntent()
}