package edu.towson.cosc435.bell.roomdemo.nav

sealed class NavRoutes(val route: String) {
    object AddRecipe : NavRoutes("addrecipe")
    object Ingredients : NavRoutes("ingredients")
    object Notes :  NavRoutes("notes")
    object NewRecipe: NavRoutes("newrecipe")
    object ViewRecipes: NavRoutes("viewrecipes")
}