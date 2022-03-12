package com.vas.feature_product_details_screen.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vas.feature_product_details_screen.data.local.dao.DetailsDao
import com.vas.feature_product_details_screen.data.local.entities.DetailsModelLocal
import com.vas.feature_product_details_screen.data.local.entities.StringListToGsonConverter

@Database(entities = [DetailsModelLocal::class], version = 1, exportSchema = false)
@TypeConverters(StringListToGsonConverter::class)
abstract class DetailsDatabase : RoomDatabase() {

    abstract fun getMainDao(): DetailsDao

    companion object {
        @Volatile private var instance: DetailsDatabase? = null

        fun getDatabase(context: Context): DetailsDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, DetailsDatabase::class.java, "details")
                .fallbackToDestructiveMigration()
                .build()
    }

}