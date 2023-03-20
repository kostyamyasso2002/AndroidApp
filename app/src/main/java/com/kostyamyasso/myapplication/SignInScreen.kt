package com.kostyamyasso.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignInScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        NewAccView()
    }
}


@Preview(showBackground = true)
@Composable
fun NewAccView() {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var keepSignedIn by remember { mutableStateOf(true) }
    var emailAboutPricing by remember { mutableStateOf(true) }

    Box(modifier = Modifier.padding(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Sign up for free", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                placeholder = { Text(text = "uzumaki_naruto") },
                label = { Text(text = "Username") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "user@gmail.com") },
                label = { Text(text = "Email") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Button(onClick = { passwordVisible = !passwordVisible }) {
                        Text(text = "Hide")
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = keepSignedIn, onCheckedChange = { keepSignedIn = it })
                        Text(text = "Keep Me Signed In")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = emailAboutPricing,
                            onCheckedChange = { emailAboutPricing = it })
                        Text(text = "Email Me About Special Pricing")
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.1f))

            val context = LocalContext.current
            Box(modifier = Modifier.padding(paddingValues = PaddingValues(bottom = 20.dp))) {
                Button(
                    onClick = {
                        if (email.isNotBlank() && password.isNotBlank()) {
                            //TODO check
                        } else {
                            Toast.makeText(
                                context,
                                "Please enter email and password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF53E88B)),
                    modifier = Modifier
                        .height(60.dp)
                        .width(200.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Create account", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}
