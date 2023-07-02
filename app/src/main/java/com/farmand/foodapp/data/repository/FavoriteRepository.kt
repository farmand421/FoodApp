package com.farmand.foodapp.data.repository

import com.farmand.foodapp.data.database.FoodDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: FoodDao) {
    fun loadAllFoods() = dao.getAllFoods()
}