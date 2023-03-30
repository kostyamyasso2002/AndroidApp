package com.kostyamyasso.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class SignInEvent {
    data class ChangeEmail(val newEmail: String) : SignInEvent()
    data class ChangePassword(val newPassword: String) : SignInEvent()
    object ChangePasswordVisibility : SignInEvent()
}

data class SignInState(
    var email: String = "",
    var password: String = "",
    var passwordVisible: Boolean = false
)

class SignInScreenViewModel : ViewModel() {
    private val _viewState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val viewState: StateFlow<SignInState> = _viewState

    fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.ChangeEmail -> {
                _viewState.value = _viewState.value.copy(email = event.newEmail)

            }
            is SignInEvent.ChangePassword -> {
                _viewState.value = _viewState.value.copy(password = event.newPassword)

            }
            SignInEvent.ChangePasswordVisibility -> {
                val prev = _viewState.value.passwordVisible
                _viewState.value = _viewState.value.copy(passwordVisible = !prev)
            }
        }
    }
}