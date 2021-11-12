package com.eslirodrigues.fruitmacro.data.repository

import com.eslirodrigues.fruitmacro.data.model.Fruit
import com.eslirodrigues.fruitmacro.data.retrofit.FruitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FruitRepository @Inject constructor(private val fruitApi: FruitApi) {

    suspend fun getAllFruits(): Flow<List<Fruit>> = flow {
        emit(fruitApi.getAllFruits())
    }.flowOn(Dispatchers.IO)
}