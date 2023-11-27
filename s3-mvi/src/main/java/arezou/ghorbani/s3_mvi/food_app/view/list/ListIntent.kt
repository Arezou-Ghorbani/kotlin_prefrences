package arezou.ghorbani.s3_mvi.food_app.view.list

sealed class ListIntent {
    object LoadFiltersLetters : ListIntent()
    object LoadRandom : ListIntent()
    object LoadCategoriesList : ListIntent()
    data class LoadFoods(val letter: String) : ListIntent()
    data class LoadSearchFoods(val search: String) : ListIntent()
    data class LoadFoodByCategory(val category: String) : ListIntent()
}
