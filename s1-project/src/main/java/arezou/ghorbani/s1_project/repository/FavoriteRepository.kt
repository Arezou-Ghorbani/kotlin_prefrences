package arezou.ghorbani.s1_project.repository

import arezou.ghorbani.s1_project.db.MoviesDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: MoviesDao) {

    fun allFavoriteList() = dao.getAllMovies()
}