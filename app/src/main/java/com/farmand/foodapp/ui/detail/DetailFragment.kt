package com.farmand.foodapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.farmand.foodapp.R
import com.farmand.foodapp.data.database.FoodEntity
import com.farmand.foodapp.data.model.home.ResponseFoodsList
import com.farmand.foodapp.databinding.FragmentDetailBinding
import com.farmand.foodapp.ui.detail.player.PlayerActivity
import com.farmand.foodapp.utils.VIDEO_ID
import com.farmand.foodapp.utils.isNetworkAvailable
import com.farmand.foodapp.utils.showSnackBar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import greyfox.rxnetwork.RxNetwork
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(), DetailContracts.View {
    //Binding
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var presenter: DetailPresenter

    @Inject
    lateinit var entity: FoodEntity
    //Other
    private val args: DetailFragmentArgs by navArgs()
    private var foodId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodId = args.foodID
        if (foodId > 0) {
            //CallApi
            presenter.callDetailApi(foodId)
        }
        //CheckInternet
        RxNetwork.init(requireContext()).observe()
            .subscribeOn(Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe {
                internetError(it.isConnected)
            }
        //Back
        binding.detailBack.setOnClickListener { findNavController().popBackStack() }


    }

    override fun loadDetail(date: ResponseFoodsList) {
        //InitViews
        binding.apply {
            date.meals?.get(0)?.let { itMral ->
                //Favorite
                entity.id = itMral.idMeal.toString().toInt()
                entity.title = itMral.strMeal.toString()
                entity.img = itMral.strMealThumb.toString()
                presenter.checkFavorite(itMral.idMeal.toString().toInt())
                //GetData
                foodCoverImg.load(itMral.strMealThumb){
                    crossfade(true)
                    crossfade(500)
                }
                foodCategoryTxt.text = itMral.strCategory
                foodAreaTxt.text = itMral.strArea
                foodTitleTxt.text = itMral.strMeal
                foodDescTxt.text = itMral.strInstructions
                //Play
                if (itMral.strYoutube !=null){
                    foodPlayImg.visibility = View.VISIBLE
                    foodPlayImg.setOnClickListener {
                        val videoId = itMral.strYoutube.split("=")[1]
                        Intent(requireContext(),PlayerActivity::class.java).also {
                            it.putExtra(VIDEO_ID,videoId)
                            startActivity(it)
                        }

                    }
                }else{
                    foodPlayImg.visibility = View.GONE
                }
                //Source
                if (itMral.strSource !=null){
                    foodSourceImg.visibility = View.VISIBLE
                    foodSourceImg.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(itMral.strSource)))

                    }
                }else{
                    foodSourceImg.visibility = View.GONE
                }
            }
            //Json Array
            val jasonData = JSONObject(Gson().toJson(date))
            val meals = jasonData.getJSONArray("meals")
            val meal = meals.getJSONObject(0)
            //Ingredient
            for (i in  1..15){
                val ingredient = meal.getString("strIngredient$i")
                if (ingredient.isNullOrEmpty().not()){
                    ingredientsTxt.append("$ingredient\n")
                }
            }
            //Measure
            for (i in  1..15){
                val measure = meal.getString("strMeasure$i")
                if (measure.isNullOrEmpty().not()){
                    measureTxt.append("$measure\n")
                }
            }
        }
    }

    override fun updateFavorite(isAdded: Boolean) {
        binding.apply {
            //Click
            detailFav.setOnClickListener {
                if (isAdded){
                    presenter.deleteFood(entity)
                }else{
                    presenter.saveFood(entity)
                }
            }
            //ChangeColor
            if (isAdded){
                detailFav.setColorFilter(ContextCompat.getColor(requireContext(),R.color.tartOrange))
            }else{
                detailFav.setColorFilter(ContextCompat.getColor(requireContext(),R.color.black))
            }
        }
    }

    override fun showLoading() {
        binding.detailLoading.visibility = View.VISIBLE
        binding.detailContentLay.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.detailLoading.visibility = View.GONE
        binding.detailContentLay.visibility = View.VISIBLE
    }

    override fun checkInternet(): Boolean {
        return requireContext().isNetworkAvailable()
    }

    override fun internetError(hasInternet: Boolean) {
        binding.apply {
            if (!hasInternet) {
                detailContentLay.visibility = View.GONE
                homeDisLay.visibility = View.VISIBLE
                //ChangeView
                disconnectLay.disImg.setImageResource(R.drawable.disconnect)
                disconnectLay.disTxt.text = getText(R.string.checkInternet)
            } else {
                detailContentLay.visibility = View.VISIBLE
                homeDisLay.visibility = View.GONE

            }
        }
    }

    override fun serverError(message: String) {
        binding.root.showSnackBar(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }
}