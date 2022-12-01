package edu.towson.cosc435.bell.roomdemo

import android.app.Application
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import edu.towson.cosc435.bell.roomdemo.recipeDB.Recipe
import edu.towson.cosc435.bell.roomdemo.recipeDB.RecipeViewModel
import androidx.lifecycle.ViewModelProvider

@Composable
fun NewRecipe(navController: NavController, jsonString: String) {
    //val viewModel = viewModel<RecipeViewModel>()
    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var newRecipe = adapter.fromJson(jsonString)
    if (newRecipe != null) {
        //viewModel.insertRecipe(newRecipe)
    }

    Text(jsonString.toString())


}