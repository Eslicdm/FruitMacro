package com.eslirodrigues.fruitmacro.data.model

data class Fruit(
    val family: String = "",
    val genus: String = "",
    val id: Int = 0,
    val name: String = "",
    val nutritions: Nutritions = Nutritions(),
    val order: String = ""
) {
    data class Nutritions(
        val calories: Int = 0,
        val carbohydrates: Double = 0.0,
        val fat: Double = 0.0,
        val protein: Double = 0.0,
        val sugar: Double = 0.0
    )
}