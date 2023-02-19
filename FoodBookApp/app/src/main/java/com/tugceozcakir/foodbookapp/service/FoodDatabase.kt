package com.tugceozcakir.foodbookapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tugceozcakir.foodbookapp.model.Food

@Database(entities = arrayOf(Food::class), version = 1)
abstract class FoodDatabase: RoomDatabase() {
    abstract fun FoodDao(): FoodDAO

    //Singleton
    companion object {
        private val lock = Any()

        @Volatile private var instance: FoodDatabase? = null
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            FoodDatabase::class.java, "foodDatabase").build()
    }

}