package com.kostyamyasso.myapplication.sign_up

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.kostyamyasso.myapplication.Logo
import com.kostyamyasso.myapplication.R
import com.kostyamyasso.myapplication.ui.theme.AndroidAppTheme


@Composable
fun SignUpScreen(signUpScreenViewModel: SignUpScreenViewModel = viewModel()) {
    val viewState by signUpScreenViewModel.viewState.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        NewAccView(viewState) {
            signUpScreenViewModel.obtainEvent(it)
        }
    }
}

@Composable
fun NewAccView(viewState: SignUpState, obtainEvent: (SignUpEvent) -> Unit) {

    Box(modifier = Modifier.padding(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.sign_up_text),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = viewState.userName,
                onValueChange = { obtainEvent(SignUpEvent.ChangeName(it)) },
                placeholder = { Text(text = stringResource(R.string.username_placeholder)) },
                label = { Text(text = stringResource(R.string.username_label)) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewState.email,
                onValueChange = { obtainEvent(SignUpEvent.ChangeEmail(it)) },
                placeholder = { Text(text = stringResource(R.string.email_placeholder)) },
                label = { Text(text = stringResource(R.string.email_label)) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewState.password,
                onValueChange = { obtainEvent(SignUpEvent.ChangePassword(it)) },
                label = { Text(text = stringResource(R.string.password_label)) },
                visualTransformation = if (viewState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Button(onClick = { obtainEvent(SignUpEvent.ChangePasswordVisibility) }) {
                        Text(text = stringResource(if (viewState.passwordVisible) R.string.password_hide else R.string.password_show))
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
                            onCheckedChange = { obtainEvent(SignUpEvent.ChangeKeepSignedIn) })
                        Text(text = stringResource(R.string.agreement_1))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = viewState.emailAboutPricing,
                            onCheckedChange = { obtainEvent(SignUpEvent.ChangeEmailAboutPricing) })
                        Text(text = stringResource(R.string.agreement_2))
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.1f))

            val context = LocalContext.current
            val warning = stringResource(R.string.login_warning)
            Box(modifier = Modifier.padding(paddingValues = PaddingValues(bottom = 20.dp))) {
                Button(
                    onClick = {
                        if (viewState.email.isNotBlank() && viewState.password.isNotBlank()) {
                            //TODO check
                        } else {
                            Toast.makeText(
                                context,
                                warning,
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
                    Text(text = stringResource(R.string.sign_up_button), color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewAccPreview() = AndroidAppTheme {
    NewAccView(viewState = SignUpState(), obtainEvent = {})
}



