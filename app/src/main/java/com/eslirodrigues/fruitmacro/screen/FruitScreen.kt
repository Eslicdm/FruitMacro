package com.eslirodrigues.fruitmacro.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.fruitmacro.R
import com.eslirodrigues.fruitmacro.ui.theme.LightMagenta
import com.eslirodrigues.fruitmacro.util.FruitApiState
import com.eslirodrigues.fruitmacro.viewmodel.FruitViewModel

@ExperimentalFoundationApi
@Composable
fun FruitScreen(
    viewModel: FruitViewModel = hiltViewModel()
) {
    var inputSearch by remember { mutableStateOf("") }

    Scaffold() {
        Column(
            modifier = Modifier
                .background(LightMagenta)
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    value = inputSearch,
                    onValueChange = {
                        inputSearch = it
                        viewModel.searchFruitList(it)
                    },
                    label = {
                        Text(text = stringResource(id = R.string.search))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    },
                    maxLines = 1,
                    singleLine = true
                )
                Icon(
                    modifier = Modifier
                        .size(44.dp)
                        .padding(top = 16.dp)
                        .clickable {
                                   /* TODO */
                        },
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.more)
                )
            }
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
                    Text(text = "Empty")
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewFruitScreen() {

}