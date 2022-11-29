package edu.towson.cosc435.bell.roomdemo.recipeDB

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel(application: Application): ViewModel() {

    val allRecipes: LiveData<List<Recipe>>
    private val repo: RecipeRepo
    val searchResults: MutableLiveData<List<Recipe>>

    init {
        val recipeDb = RecipeRoomDatabase.getInstance(application)
        val recipeDao = recipeDb.recipeDao()
        repo = RecipeRepo(recipeDao)
        allRecipes = repo.allRecipes
        searchResults = repo.searchResults
    }

    fun insertRecipe(recipe: Recipe) {
        repo.insertRecipe(recipe)
    }

    fun findRecipe(name: String) {
        repo.findRecipe(name)
    }

    fun deleteRecipe(name: String) {
        repo.deleteRecipe(name)
    }
}