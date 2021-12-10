package com.example.quizam_.presentation.card_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen
import com.example.quizam_.presentation.card_list.components.CardListCategoryBottom
import com.example.quizam_.presentation.card_list.components.QuizCardItem
import com.example.quizam_.presentation.shared_components.ScreenHeadline
import com.example.quizam_.presentation.util.truncateCategoryName


@Composable
fun CardListScreen(
    navController: NavController,
    viewModel: CardListViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            ScreenHeadline(headlineText = "card list screen")
        },
        content = {
            CardListContent(navController = navController, viewModel = viewModel)
        },
        bottomBar = {
            CardListCategoryBottom(
                categoryName = if (!viewModel.state.value.isLoading)
                    truncateCategoryName(viewModel.state.value.cards[0].category)
                else ""
            )
        }
    )
}

@Composable
fun CardListContent(
    navController: NavController,
    viewModel: CardListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val currentCard = viewModel.currentCard.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_red)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(40.dp))
            if (state.cards.isNotEmpty()) {
                if (currentCard.error == stringResource(id = R.string.last_card_error)) {
                    navController.navigate(Screen.GameResultScreen.route)
                }
                currentCard.quizCard.let { currentCard ->
                    if (currentCard != null) {
                        QuizCardItem(
                            quizCard = currentCard,
                            onSelectedOptionClick= {
                                navController.navigate(Screen.GameResultScreen.route)
                            }
                        )
                    }
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}


