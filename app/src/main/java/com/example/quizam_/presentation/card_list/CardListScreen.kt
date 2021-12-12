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
import androidx.compose.ui.graphics.Color
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

    Scaffold(
        topBar = {
            ScreenHeadline(headlineText = "score: ${userState.user?.userScore}")
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
                        if (cardDetailState.quizCard == null) {
                            navController.navigate(Screen.GameResultScreen.route)
                        } else {
                            cardDetailState.quizCard.let { currentCard ->
                                if (currentCard != null) {
                                    // quiz card item
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
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
                                                onSelectOptionClick = { viewModel.onEvent(QuizCardsEvent.ClickedOption(option_answer = option)) }
                                            )
                                        }
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


