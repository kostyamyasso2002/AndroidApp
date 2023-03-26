package com.kostyamyasso.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SignInScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        NewAccView()
    }
}

@Composable
fun NewAccView(signInScreenViewModel: SignInScreenViewModel = viewModel()) {
    val state by signInScreenViewModel.viewState.observeAsState()
    val viewState = state ?: throw Exception("Unreachable")

    Box(modifier = Modifier.padding(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Sign up for free", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = viewState.userName,
                onValueChange = { signInScreenViewModel.obtainEvent(SignInEvent.ChangeName(it)) },
                placeholder = { Text(text = "uzumaki_naruto") },
                label = { Text(text = "Username") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewState.email,
                onValueChange = { signInScreenViewModel.obtainEvent(SignInEvent.ChangeEmail(it)) },
                placeholder = { Text(text = "user@gmail.com") },
                label = { Text(text = "Email") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewState.password,
                onValueChange = { signInScreenViewModel.obtainEvent(SignInEvent.ChangePassword(it)) },
                label = { Text(text = "Password") },
                visualTransformation = if (viewState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Button(onClick = { signInScreenViewModel.obtainEvent(SignInEvent.ChangePasswordVisibility) }) {
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
                        Checkbox(
                            checked = viewState.keepSignedIn,
                            onCheckedChange = { signInScreenViewModel.obtainEvent(SignInEvent.ChangeKeepSignedIn) })
                        Text(text = "Keep Me Signed In")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = viewState.emailAboutPricing,
                            onCheckedChange = { signInScreenViewModel.obtainEvent(SignInEvent.ChangeEmailAboutPricing) })
                        Text(text = "Email Me About Special Pricing")
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.1f))

            val context = LocalContext.current
            Box(modifier = Modifier.padding(paddingValues = PaddingValues(bottom = 20.dp))) {
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
                        .width(200.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Create account", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}
