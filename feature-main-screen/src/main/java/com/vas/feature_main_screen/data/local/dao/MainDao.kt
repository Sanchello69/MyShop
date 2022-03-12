package com.vas.feature_main_screen.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vas.feature_main_screen.data.local.entity.MainModelLocal

@Dao
interface MainDao {

    @Query("Select * from MainModelLocal")
    fun getMain() : LiveData<List<MainModelLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMain(mainModelLocalList: List<MainModelLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMain(mainModelLocal: MainModelLocal)

}