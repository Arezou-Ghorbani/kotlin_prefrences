package arezou.ghorbani.s1_paging.viewmodel

import arezou.ghorbani.s1_paging.paging.MoviesPagingSourceKoin
import arezou.ghorbani.s1_paging.repository.MoviesRepositoryKoin
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MoviesViewModelKoin (private val repository: MoviesRepositoryKoin) : ViewModel() {

    val moviesList = Pager(PagingConfig(1)){
        MoviesPagingSourceKoin(repository)
    }.flow.cachedIn(viewModelScope)

}