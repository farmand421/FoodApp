package com.farmand.foodapp.ui.detail

import com.farmand.foodapp.data.database.FoodEntity
import com.farmand.foodapp.data.model.home.ResponseCategoresList
import com.farmand.foodapp.data.model.home.ResponseFoodsList
import com.farmand.foodapp.utils.base.BaseView
import com.ramand.noteapp.utils.base.BasePresenter

interface DetailContracts {
    interface View : BaseView {
        fun loadDetail(date: ResponseFoodsList)
        fun updateFavorite(isAdded: Boolean)
    }

    interface Presenter : BasePresenter {
        fun callDetailApi(id: Int)
        fun saveFood(entity: FoodEntity)
        fun deleteFood(entity: FoodEntity)
        fun checkFavorite(id: Int)
    }
}