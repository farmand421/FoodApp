package com.farmand.foodapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farmand.foodapp.utils.FOOD_DB_TABLE

@Entity(tableName = FOOD_DB_TABLE)
data class FoodEntity(
    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var img: String = ""
)
