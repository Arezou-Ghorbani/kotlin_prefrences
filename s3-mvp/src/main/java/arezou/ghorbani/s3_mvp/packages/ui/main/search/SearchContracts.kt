package arezou.ghorbani.s3_mvp.packages.ui.main.search

import arezou.ghorbani.s3_mvp.packages.data.model.home.ResponseMoviesList
import arezou.ghorbani.s3_mvp.packages.ui.base.BaseView

interface SearchContracts {

    interface View : BaseView {
        fun loadSearchData(data: ResponseMoviesList)
    }

    interface Presenter {
        fun callSearch(search: String)
    }
}