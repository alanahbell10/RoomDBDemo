package edu.towson.cosc435.bell.roomdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import edu.towson.cosc435.bell.roomdemo.nav.NavRoutes
import edu.towson.cosc435.bell.roomdemo.recipeDB.Recipe

@Composable
fun AddRecipe(navController: NavController) {

    var recipeName by remember { mutableStateOf("") }
    var recipeServings by remember { mutableStateOf("") }
    var recipeMinutes by remember { mutableStateOf("") }

    val onNameTextChange = { text : String ->
        recipeName = text
    }

    val onServingsTextChange = { text : String ->
        recipeServings = text
    }

    val onMinutesTextChange = { text : String ->
        recipeMinutes = text
    }

    val recipe = Recipe(
        recipeName,
        recipeServings,
        recipeMinutes,
        "",
        "")
    val jsonString = Gson().toJson(recipe)

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier =
    Modifier.padding(50.dp)) {
        item {
            OutlinedTextField(
                label = { Text("Recipe Name") },
                value = recipeName,
                onValueChange = onNameTextChange,
                singleLine = true
            )
        }
        item {
            OutlinedTextField(
                label = { Text("Servings") },
                value = recipeServings,
                onValueChange = onServingsTextChange,
                singleLine = true
            )
        }
        item {
            OutlinedTextField(
                label = { Text("Minutes") },
                value = recipeMinutes,
                onValueChange = onMinutesTextChange,
                singleLine = true
            )
        }
        item {
            Button(onClick = { navController.navigate(NavRoutes.Ingredients.route + "/$jsonString") }) {
                Text("Proceed")
            }
        }
    }




}