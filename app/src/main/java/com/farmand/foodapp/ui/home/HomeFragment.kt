package com.farmand.foodapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.farmand.foodapp.R
import com.farmand.foodapp.data.model.home.ResponseCategoresList
import com.farmand.foodapp.data.model.home.ResponseFoodsList
import com.farmand.foodapp.databinding.FragmentHomeBinding
import com.farmand.foodapp.ui.home.adapter.CategoriesAdapter
import com.farmand.foodapp.ui.home.adapter.FoodsAdapter
import com.farmand.foodapp.utils.isNetworkAvailable
import com.farmand.foodapp.utils.showSnackBar
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import greyfox.rxnetwork.RxNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(),HomeContracts.View {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var presenter: HomePresenter

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //CollApi
            presenter.callFoodRandom()
            presenter.callCategoriesList()
            presenter.callFoodsList("A")
            //Search
            searchEdt.textChanges()
                .skipInitialValue()
                .debounce(500,TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    if (it.toString().length > 0){
                        //CallApo
                        presenter.callSearchFood(it.toString())
                    }
                }
            //FilterFood
            filterFood()
            //CheckInternet
            RxNetwork.init(requireContext()).observe()
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe{
                    internetError(it.isConnected)
                }
        }
    }
    private fun filterFood(){
        val filters = listOf('A'..'z').flatten()
        val adapter = ArrayAdapter(requireContext(),R.layout.item_spinner,filters)
        adapter.setDropDownViewResource(R.layout.item_spinner_list)
        binding.filterSpinner.adapter = adapter
        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //CallApi
                presenter.callFoodsList(filters[p2].toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun loadFoodRandom(data: ResponseFoodsList) {
        binding.headerImg.load(data.meals?.get(0)?.strMealThumb)
    }

    override fun loadCategoriesList(data: ResponseCategoresList) {
        categoriesAdapter.setData(data.categories)
        binding.categoryList.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = categoriesAdapter
        }
        categoriesAdapter.setOnClickListener {
            presenter.callFoodsByCategory(it.strCategory.toString())
        }
    }

    override fun loadFoodsList(data: ResponseFoodsList) {
        //VisibilityMode
        binding.foodsList.visibility = View.VISIBLE
        binding.homeDisLay.visibility = View.GONE
        data.meals?.let {
        foodsAdapter.setData(it)
        }
        binding.foodsList.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = foodsAdapter
        }
        foodsAdapter.setOnItemClickListener {
            val directions = HomeFragmentDirections.actionHomeToDetail(it.idMeal!!.toInt())
            findNavController().navigate(directions)
        }
    }

    override fun foodsLoadingState(isShown: Boolean) {
        binding.apply {
            if (isShown){
                homeFoodsLoading.visibility = View.VISIBLE
                foodsList.visibility =View.GONE
            }else{
                homeFoodsLoading.visibility = View.GONE
                foodsList.visibility =View.VISIBLE
            }
        }
    }

    override fun emptyList() {
        binding.apply {
            foodsList.visibility = View.GONE
            homeDisLay.visibility = View.VISIBLE
            //ChangeView
            disconnectLay.disImg.setImageResource(R.drawable.box)
            disconnectLay.disTxt.text = getText(R.string.emptyList)
        }
    }

    override fun showLoading() {
        binding.homeCategoryLoading.visibility = View.VISIBLE
        binding.categoryList.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.homeCategoryLoading.visibility = View.GONE
        binding.categoryList.visibility = View.VISIBLE
    }

    override fun checkInternet(): Boolean {
        return requireContext().isNetworkAvailable()
    }

    override fun internetError(hasInternet: Boolean) {
        binding.apply {
            if (!hasInternet) {
                homeContent.visibility = View.GONE
                homeDisLay.visibility = View.VISIBLE
                //ChangeView
                disconnectLay.disImg.setImageResource(R.drawable.disconnect)
                disconnectLay.disTxt.text = getText(R.string.checkInternet)
            }else{
                homeContent.visibility = View.VISIBLE
                homeDisLay.visibility = View.GONE
                //CallApi
                presenter.callCategoriesList()
                presenter.callFoodsList("A")
            }
        }
    }

    override fun serverError(message: String) {
        binding.root.showSnackBar("Error")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }

}