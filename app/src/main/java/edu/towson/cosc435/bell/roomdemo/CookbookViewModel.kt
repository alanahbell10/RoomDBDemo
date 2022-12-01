package edu.towson.cosc435.bell.roomdemo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.towson.cosc435.bell.roomdemo.recipeDB.Recipe
import edu.towson.cosc435.bell.roomdemo.recipeDB.RecipeRepo
import edu.towson.cosc435.bell.roomdemo.recipeDB.RecipeRoomDatabase

class CookbookViewModel(application: Application) : ViewModel() {
    val allRecipes: LiveData<List<Recipe>>
    private val repository: RecipeRepo
    val searchResults: MutableLiveData<List<Recipe>>

    init {
        val recipeDb = RecipeRoomDatabase.getInstance(application)
        val recipeDao = recipeDb.recipeDao()
        repository = RecipeRepo(recipeDao)

        allRecipes = repository.allRecipes
        searchResults = repository.searchResults
    }

    fun insertRecipe(recipe: Recipe) {
        repository.insertRecipe(recipe)
    }

    fun findRecipe(name: String) {
        repository.findRecipe(name)
    }

    fun deleteRecipe(name: String) {
        repository.deleteRecipe(name)
    }
}

