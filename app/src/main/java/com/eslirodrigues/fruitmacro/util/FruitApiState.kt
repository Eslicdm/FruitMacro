package com.eslirodrigues.fruitmacro.util

import com.eslirodrigues.fruitmacro.data.model.Fruit

sealed class FruitApiState {
    class Success(val data: List<Fruit>) : FruitApiState()
    class Failure(val msg: Throwable) : FruitApiState()
    object Loading : FruitApiState()
    object Empty : FruitApiState()
}
