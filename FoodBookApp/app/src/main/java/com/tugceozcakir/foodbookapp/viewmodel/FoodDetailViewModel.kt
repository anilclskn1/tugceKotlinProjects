package com.tugceozcakir.foodbookapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugceozcakir.foodbookapp.model.Food
import com.tugceozcakir.foodbookapp.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application): BaseViewModel(application) {
    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(uuid : Int){
        launch {
            val dao = FoodDatabase(getApplication()).FoodDao()
            val food = dao.getFood(uuid)
            foodLiveData.value = food
        }
    }
}