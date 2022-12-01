package edu.towson.cosc435.bell.roomdemo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import edu.towson.cosc435.bell.roomdemo.CookbookViewModel
import edu.towson.cosc435.bell.roomdemo.recipeDB.Recipe

@Composable
fun ViewRecipes(
    allRecipes: List<Recipe>,
    searchResults: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
    jsonString: String
) {
    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)



    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val list = allRecipes

            item {
                TitleRow(head1 = "ID", head2 = "Recipe")
            }

            items(list) { recipe ->
                RecipeRow(id = recipe.recipeId, name = recipe.recipeName)
            }
        }
    }
}


@Composable
fun TitleRow(
    head1: String,
    head2: String,
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        Text(
            head1, color = Color.White,
            modifier = Modifier.weight(0.1f)
        )
        Text(
            head2, color = Color.White,
            modifier = Modifier.weight(0.1f)
        )
    }
}

@Composable
fun RecipeRow(
    id: Int,
    name: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier.weight(0.1f))
        Text(name, modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    )
}