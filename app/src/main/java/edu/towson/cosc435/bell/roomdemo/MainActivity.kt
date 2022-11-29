package edu.towson.cosc435.bell.roomdemo

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import edu.towson.cosc435.bell.roomdemo.recipeDB.Recipe
import edu.towson.cosc435.bell.roomdemo.recipeDB.RecipeViewModel
import edu.towson.cosc435.bell.roomdemo.ui.theme.RoomDemoTheme
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.bell.roomdemo.nav.NavRoutes


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
                    MainScreen()
                }
            }
        }
    }
}

@Composable fun ScreenSetup(viewModel: RecipeViewModel) {

    MainScreen(

    )
}

@Composable fun MainScreen(

) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = NavRoutes.AddRecipe.route
    ) {
        composable(NavRoutes.AddRecipe.route) {
            AddRecipe(navController = navController)
        }
        composable(NavRoutes.Ingredients.route + "/{jsonString}") { backStackEntry ->
            val jsonString = backStackEntry.arguments?.getString("jsonString")
            Ingredients(navController = navController, jsonString.toString())
        }
        composable(NavRoutes.Notes.route + "/{jsonWithIngredients}") { backStackEntry ->
            val jsonString = backStackEntry.arguments?.getString("jsonWithIngredients")
            Notes(navController = navController, jsonString.toString())
        }
        composable(NavRoutes.NewRecipe.route + "/{jsonWithNotes}") { backStackEntry ->
            val jsonString = backStackEntry.arguments?.getString("jsonWithNotes")
            NewRecipe(navController = navController, jsonString.toString() )
        }

    }
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String, head4: String, head5: String, head6: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        Text(head1, color = Color.White,
            modifier = Modifier.weight(0.1f))
        Text(head2, color = Color.White,
            modifier = Modifier.weight(0.1f))
        Text(head3, color = Color.White,
            modifier = Modifier.weight(0.1f))
        Text(head4, color = Color.White,
            modifier = Modifier.weight(0.1f))
        Text(head5, color = Color.White,
            modifier = Modifier.weight(0.1f))
        Text(head6, color = Color.White,
            modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun RecipeRow(id: Int, name: String, servings: Int, minutes: Int, ingredients: String, notes: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier.weight(0.1f))
        Text(name, modifier = Modifier.weight(0.1f))
        Text(servings.toString(), modifier = Modifier.weight(0.1f))
        Text(minutes.toString(), modifier = Modifier.weight(0.1f))
        Text(ingredients, modifier = Modifier.weight(0.1f))
        Text(notes, modifier = Modifier.weight(0.1f))
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
        label = { Text(title)},
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomDemoTheme {

    }
}