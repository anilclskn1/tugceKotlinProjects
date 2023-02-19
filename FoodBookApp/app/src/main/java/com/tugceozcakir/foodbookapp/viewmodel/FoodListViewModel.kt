package com.tugceozcakir.foodbookapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugceozcakir.foodbookapp.model.Food
import com.tugceozcakir.foodbookapp.service.FoodAPIService
import com.tugceozcakir.foodbookapp.service.FoodDatabase
import com.tugceozcakir.foodbookapp.util.SpecialSharedPreferences
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class FoodListViewModel(application: Application) : BaseViewModel(application) {
    val foods = MutableLiveData<List<Food>>()
    val errorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())
    private val foodApiService = FoodAPIService()
    private val disposable = CompositeDisposable()
    private var updateTime = 10 * 60 * 1000 * 1000 * 1000L


    fun refreshData(){
        val recordedTime = specialSharedPreferences.getTime()
        if(recordedTime != null && recordedTime != 0L && System.nanoTime() - recordedTime < updateTime){
            //get from SQLite
            getDataFromSqlite()
        }else{
            getDataFromInternet()
        }
    }
    fun refreshDataFromInternet(){
        getDataFromInternet()
    }
    private fun getDataFromSqlite(){
        foodLoading.value = true

        launch {
            val foodList = FoodDatabase(getApplication()).FoodDao().getAllFood()
            showFoods(foodList)
            Toast.makeText(getApplication(),"SQLite", Toast.LENGTH_SHORT).show()
        }
    }
    private fun getDataFromInternet(){
        foodLoading.value = true

        disposable.add(
            foodApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                    override fun onSuccess(t: List<Food>) {
                        hideSqlite(t)
                        Toast.makeText(getApplication(),"Internet", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = true
                        foodLoading.value = false
                        e.printStackTrace()
                    }

                }
        ))
    }
    private fun showFoods(foodsList: List<Food>){
        foods.value = foodsList
        foodLoading.value = false
        errorMessage.value = false
    }
    private fun hideSqlite(foodsList: List<Food>){
        launch {

            val dao = FoodDatabase(getApplication()).FoodDao()
            dao.deleteAllFood()
            val uuidList = dao.insertAll(*foodsList.toTypedArray())
            var i = 0
            while (i < foodsList.size){
                foodsList[i].uuid = uuidList[i].toInt()
                i = i + 1
            }
            showFoods(foodsList)
        }
        specialSharedPreferences.saveTime(System.nanoTime())
    }
}