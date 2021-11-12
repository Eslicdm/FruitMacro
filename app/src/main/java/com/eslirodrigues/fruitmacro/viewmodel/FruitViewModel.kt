package com.eslirodrigues.fruitmacro.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.fruitmacro.data.model.Fruit
import com.eslirodrigues.fruitmacro.data.repository.FruitRepository
import com.eslirodrigues.fruitmacro.util.FruitApiState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        getAllFruits()
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
            }
    }
}