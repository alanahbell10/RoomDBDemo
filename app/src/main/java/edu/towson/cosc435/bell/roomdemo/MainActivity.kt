package edu.towson.cosc435.bell.roomdemo

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.towson.cosc435.bell.roomdemo.ui.theme.RoomDemoTheme
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.bell.roomdemo.nav.NavRoutes
import edu.towson.cosc435.bell.roomdemo.recipeDB.Recipe
import edu.towson.cosc435.bell.roomdemo.screens.ViewRecipes


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: CookbookViewModel = viewModel(
                            it,
                            "CookbookViewModel",
                            CookbookViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application
                            )
                        )

                        ScreenSetup(viewModel)
                    }
                }
            }
        }
    }
    @Composable
    fun ScreenSetup(viewModel: CookbookViewModel) {

        val allRecipes by viewModel.allRecipes.observeAsState(listOf())
        val searchResults by viewModel.searchResults.observeAsState(listOf())

        MainScreen(
            allRecipes = allRecipes,
            searchResults = searchResults,
            viewModel = viewModel
        )

    }

    @Composable
    fun MainScreen(
        allRecipes: List<Recipe>,
        searchResults: List<Recipe>,
        viewModel: CookbookViewModel

    ) {
        val navController = rememberNavController()

        NavHost(
            navController = navController, startDestination = NavRoutes.AddRecipe.route
        ) {
            composable(NavRoutes.ViewRecipes.route + "/{jsonWithNotes}") { backStackEntry ->
                val newRecipe = backStackEntry.arguments?.getString("jsonWithNotes")
                ViewRecipes(allRecipes, searchResults, viewModel, navController, newRecipe.toString())
            }
            composable(NavRoutes.AddRecipe.route) {
                AddRecipe(navController = navController)
            }
            composable(NavRoutes.Ingredients.route + "/{jsonString}") { backStackEntry ->
                val jsonString = backStackEntry.arguments?.getString("jsonString")
                Ingredients(navController = navController, jsonString.toString())
            }
            composable(NavRoutes.Notes.route + "/{jsonWithIngredients}") { backStackEntry ->
                val jsonString = backStackEntry.arguments?.getString("jsonWithIngredients")
                Notes(allRecipes, viewModel, navController = navController, jsonString.toString())
            }
            composable(NavRoutes.NewRecipe.route + "/{jsonWithNotes}") { backStackEntry ->
                val jsonString = backStackEntry.arguments?.getString("jsonWithNotes")
                NewRecipe(navController = navController, jsonString.toString())
            }

        }
    }




    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        RoomDemoTheme {

        }
    }
}

class CookbookViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CookbookViewModel(application) as T
    }
}


