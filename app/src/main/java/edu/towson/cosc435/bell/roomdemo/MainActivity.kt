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
import edu.towson.cosc435.bell.roomdemo.ui.theme.RoomDemoTheme

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
                        val viewModel: RecipeViewModel = viewModel(
                            it,
                            "RecipeViewModel",
                            RecipeViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application)
                        )

                        ScreenSetup(viewModel)
                    }
                }
            }
        }
    }
}

@Composable fun ScreenSetup(viewModel: RecipeViewModel) {
    val allRecipes by viewModel.allRecipes.observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    MainScreen(
        allRecipes = allRecipes,
        searchResults = searchResults,
        viewModel = viewModel
    )
}

@Composable fun MainScreen(
    allRecipes: List<Recipe>,
    searchResults: List<Recipe>,
    viewModel: RecipeViewModel
) {
    var recipeName by remember { mutableStateOf("") }
    var recipeServings by remember { mutableStateOf("") }
    var recipeMinutes by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }

    val onRecipeTextChange = { text : String ->
        recipeName = text
    }

    val onServingsTextChange = { text : String ->
        recipeServings = text
    }

    val onMinutesTextChange = { text : String ->
        recipeMinutes = text
    }

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CustomTextField(
            title = "Recipe Name",
            textState = recipeName,
            onTextChange = onRecipeTextChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            title = "Recipe Servings",
            textState = recipeServings,
            onTextChange = onServingsTextChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            title = "Recipe Minutes",
            textState = recipeMinutes,
            onTextChange = onMinutesTextChange,
            keyboardType = KeyboardType.Text
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(onClick = {
                if (recipeServings.isNotEmpty() && recipeMinutes.isNotEmpty()) {
                    viewModel.insertRecipe(
                        Recipe(
                            recipeName,
                            recipeServings.toInt(),
                            recipeMinutes.toInt()
                        )
                    )
                    searching = false
                }
            }) {
                Text("Add")
            }

            Button(onClick = {
                searching = true
                viewModel.findRecipe(recipeName)
            }) {
                Text("Search")
            }

            Button(onClick = {
                searching = false
                viewModel.deleteRecipe(recipeName)
            }) {
                Text("Delete")
            }

            Button(onClick = {
                searching = false
                recipeName = ""
                recipeServings = ""
                recipeMinutes = ""
            }) {
                Text("Clear")
            }


            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                val list = if (searching) searchResults else allRecipes

                item {
                    TitleRow(head1 = "ID", head2 = "Recipe", head3 = "Servings", head4 = "Minutes")
                }

                items(list) { recipe ->
                    RecipeRow(id = recipe.id, name = recipe.recipeName, servings = recipe.recipeServings, minutes = recipe.recipeMinutes )
                }


            }
        }
    }
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String, head4: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f))
        Text(head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f))
        Text(head3, color = Color.White,
            modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun RecipeRow(id: Int, name: String, servings: Int, minutes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier
            .weight(0.1f))
        Text(name, modifier = Modifier.weight(0.2f))
        Text(servings.toString(), modifier = Modifier.weight(0.2f))
        Text(minutes.toString(), modifier = Modifier.weight(0.2f))
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

class RecipeViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(application) as T
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomDemoTheme {

    }
}