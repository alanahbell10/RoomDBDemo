package edu.towson.cosc435.bell.roomdemo.recipeDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.towson.cosc435.bell.roomdemo.recipeDB.Recipe

@Dao
interface RecipeDao {

    @Insert
    fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE recipeName = :name")
    fun findRecipe(name: String): List<Recipe>

    @Query("DELETE FROM recipes WHERE recipeName = :name")
    fun deleteRecipe(name: String)

}