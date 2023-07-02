package com.farmand.foodapp.ui.favorite

import com.farmand.foodapp.data.database.FoodEntity
import com.ramand.noteapp.utils.base.BasePresenter

interface FavoriteContracts {
    interface View{
        fun showAllFoods(list: MutableList<FoodEntity>)

    }
    interface Presenter: BasePresenter {
        fun loadAllFoods()
    }
}