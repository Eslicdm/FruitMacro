package com.eslirodrigues.fruitmacro.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.fruitmacro.util.FruitApiState
import com.eslirodrigues.fruitmacro.viewmodel.FruitViewModel

@Composable
fun FruitScreen(
    viewModel: FruitViewModel = hiltViewModel()
) {
    Column(Modifier.fillMaxSize()) {
        when(val result = viewModel.response.value) {
            is FruitApiState.Success -> {
                LazyColumn {
                    items(result.data) { response ->
                        FruitListItem(fruit = response)
                    }
                }
            }
            is FruitApiState.Failure -> {
                Text(text = "${result.msg}")
            }
            is FruitApiState.Loading -> {
                CircularProgressIndicator()
            }
            is FruitApiState.Empty -> {
                Text(text = "Research")
            }
        }
    }




}

@Preview
@Composable
fun PreviewFruitScreen() {

}