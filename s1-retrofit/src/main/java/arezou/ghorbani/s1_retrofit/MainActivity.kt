package arezou.ghorbani.s1_retrofit

import arezou.ghorbani.s1_retrofit.databinding.ActivityMainBinding
import arezou.ghorbani.s1_retrofit.model.ResponseMoviesList
import arezou.ghorbani.s1_retrofit.server.ApiClient
import arezou.ghorbani.s1_retrofit.server.ApiServices
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Adapter
    private val moviesAdapter by lazy { MoviesAdapter() }

    //ApiServices
    private val api: ApiServices by lazy {
        ApiClient().getClient().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //show loading
            moviesLoader.visibility = View.VISIBLE
            //Call movies api
            val callMoviesApi = api.moviesList(1)
            callMoviesApi.enqueue(object : Callback<ResponseMoviesList> {
                override fun onResponse(call: Call<ResponseMoviesList>, response: Response<ResponseMoviesList>) {
                    moviesLoader.visibility = View.GONE
                    if (response.isSuccessful) {
                        response.body()?.let { itBody ->
                            itBody.data?.let { itData ->
                                if (itData.isNotEmpty()) {
                                    moviesAdapter.differ.submitList(itData)
                                    //Recycler
                                    moviesRecycler.apply {
                                        layoutManager = LinearLayoutManager(this@MainActivity)
                                        adapter = moviesAdapter
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMoviesList>, t: Throwable) {
                    moviesLoader.visibility = View.GONE
                    Log.e("onFailure", "Err : ${t.message}")
                }
            })
        }
    }
}