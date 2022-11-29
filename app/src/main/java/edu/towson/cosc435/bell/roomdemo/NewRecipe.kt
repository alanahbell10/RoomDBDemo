package edu.towson.cosc435.bell.roomdemo

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NewRecipe(navController: NavController, jsonString: String) {
    Text(jsonString.toString())

}