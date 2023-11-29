package arezou.ghorbani.s3_mvp.retrofit.ui.detail

import arezou.ghorbani.s3_mvp.base.BasePresenter
import arezou.ghorbani.s3_mvp.base.BaseView
import arezou.ghorbani.s3_mvp.retrofit.data.database.FoodEntity
import arezou.ghorbani.s3_mvp.retrofit.data.model.home.ResponseFoodsList

interface DetailContracts {
    interface View : BaseView {
        fun loadDetail(data: ResponseFoodsList)
        fun updateFavorite(isAdded: Boolean)
    }

    interface Presenter : BasePresenter {
        fun callDetailApi(id: Int)
        fun saveFood(entity: FoodEntity)
        fun deleteFood(entity: FoodEntity)
        fun checkFavorite(id: Int)
    }
}