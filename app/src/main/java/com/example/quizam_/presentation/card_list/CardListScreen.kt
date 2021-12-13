package com.example.quizam_.presentation.card_list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen
import com.example.quizam_.presentation.card_list.components.CardListCategoryBottom
import com.example.quizam_.presentation.card_list.components.QuizCardOption
import com.example.quizam_.presentation.shared_components.ScreenHeadline
import com.example.quizam_.presentation.util.parseText
import com.example.quizam_.presentation.util.truncateCategoryName


@Composable
fun CardListScreen(
    navController: NavController,
    viewModel: CardListViewModel = hiltViewModel(),
) {
    val cardListState = viewModel.cardListState.value
    val cardDetailState = viewModel.cardDetailState.value
    val userState = viewModel.userState.value
    val context = LocalContext.current

    BackHandler() {
        navController.navigate(Screen.GameStartScreen.route)
    }

    Scaffold(
        topBar = {
            ScreenHeadline(headlineText = "${userState.user?.userScore} points")
        },
        content = {
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
                    if (cardListState.cards.isNotEmpty()) {
                        if (cardDetailState.quizCard != null) {
                            cardDetailState.quizCard.let { currentCard ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.CenterHorizontally)
                                ) {
                                    Text(
                                        text = parseText(currentCard.question),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.h2,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.size(20.dp))
                                    currentCard.options.forEach { option ->
                                        QuizCardOption(
                                            option = parseText(option),
                                            onSelectOptionClick = {
                                                viewModel.onEvent(
                                                    event = QuizCardsEvent.ClickedOption(
                                                        option_answer = option
                                                    ),
                                                    context = context
                                                )
                                                if (cardListState.cards[cardListState.cards.size - 1] == currentCard) {
                                                    navController.navigate(Screen.GameResultScreen.route)
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                if (cardListState.error.isNotBlank()) {
                    Text(
                        text = cardListState.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
                if (cardListState.isLoading) {
                    CircularProgressIndicator()
                }
            }
        },
        bottomBar = {
            CardListCategoryBottom(
                categoryName = if (!viewModel.cardListState.value.isLoading)
                    truncateCategoryName(viewModel.cardListState.value.cards[0].category)
                else ""
            )
        }
    )
}