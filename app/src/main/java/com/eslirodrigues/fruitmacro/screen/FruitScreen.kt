package com.eslirodrigues.fruitmacro.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.fruitmacro.ui.theme.LightMagenta
import com.eslirodrigues.fruitmacro.util.FruitApiState
import com.eslirodrigues.fruitmacro.viewmodel.FruitViewModel

@ExperimentalFoundationApi
@Composable
fun FruitScreen(
    viewModel: FruitViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .background(LightMagenta)
            .fillMaxSize()) {
        when(val result = viewModel.response.value) {
            is FruitApiState.Success -> {
                LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                    items(result.data) { response ->
                        FruitListItem(fruit = response)
                    }
                }
            }
            is FruitApiState.Failure -> {
                Text(text = "${result.msg}")
            }
            is FruitApiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
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