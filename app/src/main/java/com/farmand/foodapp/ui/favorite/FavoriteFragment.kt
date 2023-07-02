package com.farmand.foodapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.farmand.foodapp.data.database.FoodEntity
import com.farmand.foodapp.databinding.FragmentFavoriteBinding
import com.farmand.foodapp.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment(), FavoriteContracts.View {
    //Binding
    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var presenter: FavoritePresenter

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //LoadDataFromDatabase
        presenter.loadAllFoods()
    }

    override fun showAllFoods(list: MutableList<FoodEntity>) {
        favoriteAdapter.setData(list)
        binding.favoriteList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }

        favoriteAdapter.setOnItemClickListener {
            val directions = HomeFragmentDirections.actionHomeToDetail(it.id)
            findNavController().navigate(directions)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }
}