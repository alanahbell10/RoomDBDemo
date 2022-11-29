package edu.towson.cosc435.bell.roomdemo.recipeDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "recipes")
class Recipe {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipeId")
    var recipeId: Int = 0

    @ColumnInfo(name = "recipeName")
    var recipeName: String = ""

    @ColumnInfo(name = "recipeServings")
    var recipeServings: String = ""

    @ColumnInfo(name = "recipeMinutes")
    var recipeMinutes: String = ""

    @ColumnInfo(name = "recipeIngredients")
    var recipeIngredients: String = ""

    @ColumnInfo(name = "recipeNotes")
    var recipeNotes: String = ""


    constructor(
        recipeName: String,
        recipeServings: String,
        recipeMinutes: String,
        recipeIngredients: String,
        recipeNotes: String
    ) {
        this.recipeName = recipeName
        this.recipeServings = recipeServings
        this.recipeMinutes = recipeMinutes
        this.recipeIngredients = recipeIngredients
        this.recipeNotes = recipeNotes
    }
}