package edu.towson.cosc435.bell.roomdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "recipes")
class Recipe {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "recipeId")
    var id: Int = 0

    @ColumnInfo(name = "recipeName")
    var recipeName: String = ""

    @ColumnInfo(name = "recipeServings")
    var recipeServings: Int = 0

    @ColumnInfo(name = "recipeMinutes")
    var recipeMinutes: Int = 0

    constructor() {}

    constructor(id: Int, recipeName: String, recipeServings: Int, recipeMinutes: Int) {
        this.recipeName = recipeName
        this.recipeServings = recipeServings
        this.recipeMinutes = recipeMinutes
    }
    constructor(recipeName: String, recipeServings: Int, recipeMinutes: Int) {
        this.recipeName = recipeName
        this.recipeServings = recipeServings
        this.recipeMinutes = recipeMinutes
    }


}