package arezou.ghorbani.s3_mvi.food_app.ui.favorite

import arezou.ghorbani.s3_mvi.R
import arezou.ghorbani.s3_mvi.databinding.FragmentFoodsFavoriteBinding
import arezou.ghorbani.s3_mvi.food_app.ui.list.FoodsListFragmentDirections
import arezou.ghorbani.s3_mvi.food_app.utils.isVisible
import arezou.ghorbani.s3_mvi.food_app.utils.setupRecyclerView
import arezou.ghorbani.s3_mvi.food_app.view.favorite.FavoriteIntent
import arezou.ghorbani.s3_mvi.food_app.view.favorite.FavoriteState
import arezou.ghorbani.s3_mvi.food_app.view.favorite.FavoriteViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodsFavoriteFragment : Fragment() {
    //Binding
    private var _binding: FragmentFoodsFavoriteBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    //Other
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFoodsFavoriteBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding?.apply {
            //Scope
            lifecycleScope.launchWhenCreated {
                //Send
                viewModel.favoriteIntent.send(FavoriteIntent.LoadFavorites)
                //Load data
                viewModel.state.collect { state ->
                    when (state) {
                        is FavoriteState.Empty -> {
                            emptyLay.isVisible(true, favoriteList)
                            statusLay.disImg.setImageResource(R.drawable.box)
                            statusLay.disTxt.text = getString(R.string.emptyList)
                        }
                        is FavoriteState.LoadFavorites -> {
                            emptyLay.isVisible(false, favoriteList)
                            favoriteAdapter.setData(state.data)
                            favoriteList.setupRecyclerView(LinearLayoutManager(requireContext()), favoriteAdapter)

                            favoriteAdapter.setOnItemClickListener {
                                val direction = FoodsListFragmentDirections.actionListToDetail(it.id)
                                findNavController().navigate(direction)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}