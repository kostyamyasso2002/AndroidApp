package com.kostyamyasso.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SignInScreen(signInScreenViewModel: SignInScreenViewModel = viewModel()) {
    val viewState by signInScreenViewModel.viewState.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        Spacer(modifier = Modifier.height(30.dp))
        LoginView(viewState) {
            signInScreenViewModel.obtainEvent(it)
        }
    }
}

@Composable
fun LoginView(viewState: SignInState, obtainEvent: (SignInEvent) -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.login_text),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = viewState.email,
            onValueChange = { obtainEvent(SignInEvent.ChangeEmail(it)) },
            placeholder = { Text(text = stringResource(R.string.email_placeholder)) },
            label = { Text(text = stringResource(R.string.email_label)) },
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = viewState.password,
            onValueChange = { obtainEvent(SignInEvent.ChangePassword(it)) },
            label = { Text(text = stringResource(R.string.password_label)) },
            visualTransformation = if (viewState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Button(onClick = { obtainEvent(SignInEvent.ChangePasswordVisibility) }) {
                    Text(
                        text = stringResource(if (viewState.passwordVisible) R.string.password_hide else R.string.password_show)
                    )
                }
            },
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        val context = LocalContext.current
        val loginWarning = stringResource(R.string.login_warning)
        Box(modifier = Modifier.padding(40.dp)) {
            Button(
                onClick = {
                    if (viewState.email.isNotBlank() && viewState.password.isNotBlank()) {
                        //TODO check
                    } else {
                        Toast.makeText(
                            context,
                            loginWarning,
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
                Text(
                    text = stringResource(R.string.login_button),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginView(viewState = SignInState(), obtainEvent = {})
}
