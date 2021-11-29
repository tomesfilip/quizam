package com.example.quizam_.presentation.category_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.quizam_.R
import com.example.quizam_.presentation.Screen
import com.example.quizam_.presentation.category_list.components.CategoryListItem
import com.example.quizam_.presentation.shared_components.ScreenHeadline

@ExperimentalFoundationApi
@Composable
fun CategoryListScreen(
    navController: NavController,
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ScreenHeadline(headlineText = stringResource(id = R.string.headline_category_list))
        },
        content = {
            CategoryContent(navController = navController, viewModel = viewModel)
        }
    )

}

@ExperimentalFoundationApi
@Composable
fun CategoryContent(
    navController: NavController,
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_red))) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(32.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.categories) { category ->
                CategoryListItem(
                    quizCategory = category,
                    onItemClick =  {
                        navController.navigate(Screen.CardListScreen.route + "/${category.id}")
                    }
                )
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}