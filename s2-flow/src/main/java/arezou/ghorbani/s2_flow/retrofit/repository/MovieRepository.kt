package arezou.ghorbani.s2_flow.retrofit.repository

import arezou.ghorbani.s2_flow.retrofit.api.ApiServices
import arezou.ghorbani.s2_flow.retrofit.model.ResponseMovies
import arezou.ghorbani.s2_flow.utils.MyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: ApiServices) {

    suspend fun moviesList(): Flow<MyResponse<ResponseMovies>> {
        return flow {
            emit(MyResponse.loading())

            val result = api.moviesList(1)

            emit(MyResponse.success(result.body()))
        }.catch { emit(MyResponse.error(it.message.toString())) }
            .flowOn(Dispatchers.IO)
    }
}