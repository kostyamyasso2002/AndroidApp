package com.kostyamyasso.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel


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
fun LoginView(loginViewModel: SignUpScreenViewModel = viewModel()) {
    val state by loginViewModel.viewState.observeAsState()
    val viewState = state ?: return



    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login to your account", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = viewState.email,
            onValueChange = { loginViewModel.obtainEvent(SignUpEvent.ChangeEmail(it)) },
            placeholder = { Text(text = "user@gmail.com") },
            label = { Text(text = "Email") },
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = viewState.password,
            onValueChange = { loginViewModel.obtainEvent(SignUpEvent.ChangePassword(it)) },
            label = { Text(text = "Password") },
            visualTransformation = if (viewState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Button(onClick = { loginViewModel.obtainEvent(SignUpEvent.ChangePasswordVisibility) }) {
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
                    if (viewState.email.isNotBlank() && viewState.password.isNotBlank()) {
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
