package com.example.sf333as1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Number()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Number() {
    var secret by remember { mutableStateOf(Random.nextInt(1,1001)) }
    var guess by remember { mutableStateOf("") }
    var hint by remember { mutableStateOf("") }
    var number by remember { mutableStateOf(0) }
    var gameover by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Try to guess the number I'm thinking of from 1-1000",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom =16.dp)
        )


        OutlinedTextField(
            value = guess,
            onValueChange = {
                guess = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            label = { Text("Enter a number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if(!gameover){
                    val user = guess.toIntOrNull()
                    if (user != null){
                        number++
                        when {
                            user == secret -> {
                                hint = "Congratulation You guessed it in $number times."
                                gameover = true
                            }

                            user < secret -> {
                                hint = "Hint:It's Higher!"
                            }

                            else -> {
                                hint = "Hint:It's Lower!"
                            }
                        }
                    }else {
                        hint = "Please enter number first"

                    }
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guess")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = hint,color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                secret = Random.nextInt(1,1001)
                guess = ""
                hint = ""
                number = 0
                gameover = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Play Again")

        }
    }
}