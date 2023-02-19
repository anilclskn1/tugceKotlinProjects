package com.tugceozcakir.foodbookapp.service

import com.tugceozcakir.foodbookapp.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {
    //GET, POST

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    //BASE_URL -> https://raw.githubusercontent.com/
    //atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFood(): Single<List<Food>>
}