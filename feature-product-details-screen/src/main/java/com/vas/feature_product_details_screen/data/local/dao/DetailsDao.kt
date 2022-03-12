package com.vas.feature_product_details_screen.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vas.feature_product_details_screen.data.local.entities.DetailsModelLocal

@Dao
interface DetailsDao {

    @Query("Select * from DetailsModelLocal")
    fun getDetails() : LiveData<List<DetailsModelLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetails(detailsModelLocalList: List<DetailsModelLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(detailsModelLocal: DetailsModelLocal)

}