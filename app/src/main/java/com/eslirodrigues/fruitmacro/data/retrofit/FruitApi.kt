package com.eslirodrigues.fruitmacro.data.retrofit

import com.eslirodrigues.fruitmacro.data.model.Fruit
import retrofit2.http.GET

interface FruitApi {

    @GET("api/fruit/all")
    suspend fun getAllFruits(): List<Fruit>
}