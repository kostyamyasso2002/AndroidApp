package com.kostyamyasso.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
fun SignUpScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        Spacer(modifier = Modifier.height(30.dp))
        LoginView()
    }
}


@Preview(showBackground = true)
@Composable
fun LoginView() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login to your account", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = "user@gmail.com") },
            label = { Text(text = "Email") },
            shape = RoundedCornerShape(12.dp)
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
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        val context = LocalContext.current
        Box(modifier = Modifier.padding(40.dp)) {
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
                    .width(140.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Login", color = Color.White, fontSize = 18.sp)
            }
        }
    }

}