package com.example.composelearning.navigation

sealed class Screen(val route : String){
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg  args : String) : String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
