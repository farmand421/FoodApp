package com.farmand.foodapp.ui.home

import com.farmand.foodapp.data.model.home.ResponseCategoresList
import com.farmand.foodapp.data.model.home.ResponseFoodsList
import com.farmand.foodapp.utils.base.BaseView
import com.ramand.noteapp.utils.base.BasePresenter

interface HomeContracts {
    interface View: BaseView{
        fun loadFoodRandom(data: ResponseFoodsList)
        fun loadCategoriesList(data: ResponseCategoresList)
        fun loadFoodsList(data: ResponseFoodsList)
        fun foodsLoadingState(isShown: Boolean)
        fun emptyList()
    }
    interface Presenter: BasePresenter {
        fun callFoodRandom()
        fun callCategoriesList()
        fun callFoodsList(letter: String)
        fun callSearchFood(letter: String)
        fun callFoodsByCategory(letter: String)
    }
}