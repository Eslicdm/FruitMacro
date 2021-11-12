package com.eslirodrigues.fruitmacro.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eslirodrigues.fruitmacro.data.model.Fruit

@Composable
fun FruitListItem(fruit: Fruit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(14.dp))
    ) {
        Row {
            Text(text = fruit.name, color = Color.Red)
        }
    }

}

@Preview
@Composable
fun PreviewFruitListItem() {

}