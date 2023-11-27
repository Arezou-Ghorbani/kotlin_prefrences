package arezou.ghorbani.s3_mvi.food_app.ui.list

import arezou.ghorbani.s3_mvi.R
import arezou.ghorbani.s3_mvi.databinding.FragmentFoodsListBinding
import arezou.ghorbani.s3_mvi.food_app.ui.list.adapters.CategoriesAdapter
import arezou.ghorbani.s3_mvi.food_app.ui.list.adapters.FoodsAdapter
import arezou.ghorbani.s3_mvi.food_app.utils.isVisible
import arezou.ghorbani.s3_mvi.food_app.utils.network.ConnectivityStatus
import arezou.ghorbani.s3_mvi.food_app.utils.network.NetworkConnectivity
import arezou.ghorbani.s3_mvi.food_app.utils.setupListWithAdapter
import arezou.ghorbani.s3_mvi.food_app.utils.setupRecyclerView
import arezou.ghorbani.s3_mvi.food_app.view.list.ListIntent
import arezou.ghorbani.s3_mvi.food_app.view.list.ListState
import arezou.ghorbani.s3_mvi.food_app.view.list.ListViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodsListFragment : Fragment() {
    //Binding
    private var _binding: FragmentFoodsListBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    @Inject
    lateinit var networkConnectivity: NetworkConnectivity

    //Other
    private val viewModel: ListViewModel by viewModels()

    enum class PageState { EMPTY, NETWORK, NONE }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFoodsListBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding?.apply {
            //Lifecycle
            lifecycleScope.launchWhenCreated {
                //Get data
                viewModel.state.collect { state ->
                    when (state) {
                        is ListState.Idle -> {}
                        is ListState.FilterLetters -> {
                            filterSpinner.setupListWithAdapter(state.letters) {
                                lifecycleScope.launchWhenCreated {
                                    viewModel.intentChannel.send(ListIntent.LoadFoods(it))
                                }
                            }
                        }
                        is ListState.RandomFood -> {
                            if (state.food != null) {
                                headerImg.load(state.food.strMealThumb) {
                                    crossfade(true)
                                    crossfade(500)
                                }
                            }
                        }
                        is ListState.LoadingCategory -> {
                            homeCategoryLoading.isVisible(true, categoryList)
                        }
                        is ListState.CategoriesList -> {
                            homeCategoryLoading.isVisible(false, categoryList)
                            categoriesAdapter.setData(state.categories)
                            categoryList.setupRecyclerView(
                                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                                categoriesAdapter
                            )

                            categoriesAdapter.setOnItemClickListener {
                                lifecycleScope.launchWhenCreated {
                                    viewModel.intentChannel.send(ListIntent.LoadFoodByCategory(it.strCategory!!))
                                }
                            }
                        }
                        is ListState.LoadingFoods -> {
                            homeFoodsLoading.isVisible(true, foodsList)
                        }
                        is ListState.FoodsList -> {
                            checkConnectionOrEmpty(false, PageState.NONE)
                            homeFoodsLoading.isVisible(false, foodsList)
                            foodsAdapter.setData(state.foods)
                            foodsList.setupRecyclerView(
                                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                                foodsAdapter
                            )

                            foodsAdapter.setOnItemClickListener {
                                val direction = FoodsListFragmentDirections.actionListToDetail(it.idMeal?.toInt()!!)
                                findNavController().navigate(direction)
                            }
                        }
                        is ListState.Empty -> {
                            checkConnectionOrEmpty(true, PageState.EMPTY)
                        }
                        is ListState.Error -> {
                            Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            //Search
            searchEdt.addTextChangedListener {
                if (it.toString().length > 2) {
                    lifecycleScope.launchWhenCreated {
                        viewModel.intentChannel.send(ListIntent.LoadSearchFoods(it.toString()))
                    }
                }
            }
            //Check internet
            lifecycleScope.launchWhenCreated {
                networkConnectivity.observe().collect {
                    when (it) {
                        ConnectivityStatus.Status.Available -> {
                            checkConnectionOrEmpty(false, PageState.NONE)
                            viewModel.intentChannel.send(ListIntent.LoadFiltersLetters)
                            viewModel.intentChannel.send(ListIntent.LoadRandom)
                            viewModel.intentChannel.send(ListIntent.LoadCategoriesList)
                            viewModel.intentChannel.send(ListIntent.LoadFoods("A"))
                        }
                        ConnectivityStatus.Status.Unavailable -> {}
                        ConnectivityStatus.Status.Losing -> {}
                        ConnectivityStatus.Status.Lost -> {
                            checkConnectionOrEmpty(true, PageState.NETWORK)
                        }
                    }
                }
            }
        }
    }

    private fun checkConnectionOrEmpty(isShownError: Boolean, state: PageState) {
        binding?.apply {
            if (isShownError) {
                homeDisLay.isVisible(true, homeContent)
                when (state) {
                    PageState.EMPTY -> {
                        statusLay.disImg.setImageResource(R.drawable.box)
                        statusLay.disTxt.text = getString(R.string.emptyList)
                    }
                    PageState.NETWORK -> {
                        statusLay.disImg.setImageResource(R.drawable.disconnect)
                        statusLay.disTxt.text = getString(R.string.checkInternet)
                    }
                    else -> {}
                }
            } else {
                homeDisLay.isVisible(false, homeContent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}