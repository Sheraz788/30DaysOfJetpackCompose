package com.example.composelearning.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(Screen.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(Screen.SignUpScreen.route){
            SignUpScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{userInfo}",
            arguments = listOf(
                navArgument("userInfo"){
                    type = NavType.StringType
                    defaultValue = "Sheraz"
                    nullable = true
                }
            )
        ){ entry ->
            DetailScreen(userInfo = entry.arguments?.getString("userInfo"))
        }
    }
}


@Composable
fun LoginScreen(navController: NavController){
    var emailAddress by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Surface(
        color = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,

            ) {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),

                ) {
                Text(
                    text = "Log in ",
                    color = Color.Green,
                    fontSize = 18.sp
                )
                Text(
                    text = "to your account",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Email or UserName",
                color = Color.Black,
                fontSize = 12.sp
            )

            OutlinedTextField(
                value = emailAddress,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
                onValueChange = {
                    emailAddress = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Email or UserName")}
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Password",
                color = Color.Black,
                fontSize = 12.sp
            )

            OutlinedTextField(
                value = password,
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
                onValueChange = {
                    password = it
                },
                placeholder = { Text(text = "Password")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.DetailScreen.withArgs(emailAddress))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
            ) {
                Text(
                    text = "Login"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                ) {
                Text(
                    text = "Don't have an account? ",
                    color = Color.Black,
                    fontSize = 18.sp
                )
                Text(
                    text = "Sign up",
                    color = Color.Green,
                    fontSize = 18.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.SignUpScreen.route)
                    }
                )
            }

        }
    }
}

@Composable
fun SignUpScreen(navController: NavController){
    var userName by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var emailAddress by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var confirmPassword by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Surface(
        color = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "UserName",
                color = Color.Black,
                fontSize = 12.sp
            )
            OutlinedTextField(
                value = userName,
                onValueChange = {name -> userName = name},
                placeholder = {Text("Name")},
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = "Email Address",
                color = Color.Black,
                fontSize = 12.sp
            )
            OutlinedTextField(
                value = emailAddress,
                onValueChange = {email -> emailAddress = email},
                placeholder = {Text("Email")},
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = "Password",
                color = Color.Black,
                fontSize = 12.sp
            )
            OutlinedTextField(
                value = password,
                onValueChange = {pass -> password = pass},
                placeholder = {Text("Password")},
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Confirm Password",
                color = Color.Black,
                fontSize = 12.sp
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {confirmPass -> confirmPassword = confirmPass},
                placeholder = {Text("Confirm Password")},
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.DetailScreen.withArgs(userName.text))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
            ) {
                Text(
                    text = "Sign Up"
                )
            }

        }

    }
}

@Composable
fun DetailScreen(userInfo : String?){
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello, $userInfo")
    }
}
