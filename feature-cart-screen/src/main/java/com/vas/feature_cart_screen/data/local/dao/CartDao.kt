package com.vas.feature_cart_screen.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vas.feature_cart_screen.data.local.entity.CartModelLocal

@Dao
interface CartDao {

    @Query("Select * from CartModelLocal")
    fun getCart() : LiveData<List<CartModelLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCart(cartModelLocalList: List<CartModelLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cartModelLocal: CartModelLocal)

}