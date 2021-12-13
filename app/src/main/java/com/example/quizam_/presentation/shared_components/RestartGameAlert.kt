package com.example.quizam_.presentation.shared_components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.quizam_.presentation.Screen

@Composable
fun QuitGameAlert(navController: NavController) {
    val openDialog = remember { mutableStateOf(false)  }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text="Restart game")
            },
            text = {
                Text(text="Do you want to restart the game?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        navController.navigate(Screen.GameStartScreen.route)
                    }
                ) {
                    Text(text="Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDialog.value = false }
                ) {
                    Text(text="No")
                }
            }

        )
    }

}