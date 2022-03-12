package com.vas.myshop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vas.feature_cart_screen.data.local.dao.CartDao
import com.vas.feature_cart_screen.data.local.entity.CartModelLocal
import com.vas.feature_product_details_screen.data.local.dao.DetailsDao
import com.vas.feature_product_details_screen.data.local.entity.DetailsModelLocal

@Database(entities = [DetailsModelLocal::class, CartModelLocal::class], version = 1, exportSchema = false)
@TypeConverters(DataClass::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun getMainDao(): DetailsDao
    abstract fun getCartDao(): CartDao

    companion object {
        @Volatile private var instance: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, LocalDatabase::class.java, "details")
                .fallbackToDestructiveMigration()
                .build()
    }

}