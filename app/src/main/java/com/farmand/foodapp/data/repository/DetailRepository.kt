package com.farmand.foodapp.data.repository

import com.farmand.foodapp.data.database.FoodDao
import com.farmand.foodapp.data.database.FoodEntity
import com.farmand.foodapp.data.server.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices, private val dao: FoodDao) {
    fun loadFoodDetail(id: Int) = api.foodDetail(id)

    fun saveFood(entity: FoodEntity) = dao.saveFood(entity)
    fun deleteFood(entity: FoodEntity) = dao.deleteFood(entity)
    fun existsFood(id: Int) = dao.existsFood(id)
}