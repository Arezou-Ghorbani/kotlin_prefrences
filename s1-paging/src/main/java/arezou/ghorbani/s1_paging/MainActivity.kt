package arezou.ghorbani.s1_paging

import arezou.ghorbani.s1_paging.adapter.LoadMoreAdapter
import arezou.ghorbani.s1_paging.adapter.MoviesAdapterKoin
import arezou.ghorbani.s1_paging.databinding.ActivityMainBinding
import arezou.ghorbani.s1_paging.viewmodel.MoviesViewModelKoin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Hilt
/*    //Inject
    private val viewModel: MoviesViewModel by viewModels()

    @Inject
    lateinit var moviesAdapter: MoviesAdapter*/

    //Koin
    private val viewModel: MoviesViewModelKoin by inject()
    private val moviesAdapter: MoviesAdapterKoin by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Load data
            lifecycleScope.launchWhenCreated {
                viewModel.moviesList.collect {
                    moviesAdapter.submitData(it)
                }
            }
            //Loading
            lifecycleScope.launchWhenCreated {
                moviesAdapter.loadStateFlow.collect {
                    val state = it.refresh

                    moviesLoading.isVisible = state is LoadState.Loading
                }
            }
            //RecyclerView
            moviesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = moviesAdapter
            }
            //SwipeRefresh
            movieSwipe.setOnRefreshListener {
                movieSwipe.isRefreshing = false
                moviesAdapter.refresh()
            }
            //Load more
            moviesRecycler.adapter = moviesAdapter.withLoadStateFooter(
                LoadMoreAdapter { moviesAdapter.retry() }
            )
        }
    }
}