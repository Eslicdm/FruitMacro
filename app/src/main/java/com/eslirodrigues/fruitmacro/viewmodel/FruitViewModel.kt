package com.eslirodrigues.fruitmacro.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.fruitmacro.data.model.Fruit
import com.eslirodrigues.fruitmacro.data.repository.FruitRepository
import com.eslirodrigues.fruitmacro.util.FruitApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    private val fruitRepository: FruitRepository
): ViewModel() {
    val response: MutableState<FruitApiState> = mutableStateOf(FruitApiState.Empty)

    private var fruitList = listOf<Fruit>()
    private var cachedFruitList = listOf<Fruit>()
    private var isSearchStarting = true

    init {
        getAllFruits()
    }

    fun searchFruitList(query: String) {
        val listToSearch = if(isSearchStarting) {
            fruitList
        } else {
            cachedFruitList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                fruitList = cachedFruitList
                response.value = FruitApiState.Success(fruitList)
                return@launch
            }
            val results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                cachedFruitList = fruitList
                isSearchStarting = false
            }
            fruitList = results
            response.value = FruitApiState.Success(fruitList)
        }
    }

    fun getAllFruits() = viewModelScope.launch {
        fruitRepository.getAllFruits()
            .onStart {
                response.value = FruitApiState.Loading
            }
            .catch {
                response.value = FruitApiState.Failure(it)
            }.collect {
                response.value = FruitApiState.Success(it)
                fruitList = it
            }
    }
}