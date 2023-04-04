package com.kostyamyasso.myapplication.sign_in

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

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

@HiltViewModel
class SignInScreenViewModel @Inject constructor() : ViewModel() {
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