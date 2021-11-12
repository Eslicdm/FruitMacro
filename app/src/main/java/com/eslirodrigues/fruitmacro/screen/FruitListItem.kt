package com.eslirodrigues.fruitmacro.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.fruitmacro.data.model.Fruit
import com.eslirodrigues.fruitmacro.ui.theme.DarkMagenta
import com.eslirodrigues.fruitmacro.ui.theme.LightMagenta

@Composable
fun FruitListItem(fruit: Fruit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(14.dp))
    ) {
        val fruitName = fruit.name
        val calories = fruit.nutritions.calories.toString()
        val carbohydrates = fruit.nutritions.carbohydrates.toString()
        val protein = fruit.nutritions.protein.toString()
        val fat = fruit.nutritions.fat.toString()

        Column(modifier = Modifier
            .background(DarkMagenta)
            .padding(10.dp)) {
            Text(text = fruitName, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "Calories: $calories")
            Text(text = "Carbohydrates: $carbohydrates")
            Text(text = "Protein: $protein")
            Text(text = "Fat: $fat")
        }
    }

}

@Preview
@Composable
fun PreviewFruitListItem() {

}