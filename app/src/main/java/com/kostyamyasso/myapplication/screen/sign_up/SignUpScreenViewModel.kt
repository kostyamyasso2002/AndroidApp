package com.kostyamyasso.myapplication.screen.sign_up

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

sealed class SignUpEvent {
    data class ChangeName(val newName: String) : SignUpEvent()
    data class ChangeEmail(val newEmail: String) : SignUpEvent()
    data class ChangePassword(val newPassword: String) : SignUpEvent()
    object ChangePasswordVisibility : SignUpEvent()
    object ChangeKeepSignedIn : SignUpEvent()
    object ChangeEmailAboutPricing : SignUpEvent()
}

data class SignUpState(
    var userName: String = "",
    var email: String = "",
    var password: String = "",
    var passwordVisible: Boolean = false,
    var keepSignedIn: Boolean = true,
    var emailAboutPricing: Boolean = true
)

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(): ViewModel() {
    private val _viewState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val viewState: StateFlow<SignUpState> = _viewState

    fun obtainEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.ChangeName -> {
                _viewState.value = _viewState.value.copy(userName = event.newName)

            }
            is SignUpEvent.ChangeEmail -> {
                _viewState.value = _viewState.value.copy(email = event.newEmail)

            }
            is SignUpEvent.ChangePassword -> {
                _viewState.value = _viewState.value.copy(password = event.newPassword)

            }
            SignUpEvent.ChangePasswordVisibility -> {
                val prev = _viewState.value.passwordVisible
                _viewState.value = _viewState.value.copy(passwordVisible = !prev)
            }
            SignUpEvent.ChangeKeepSignedIn -> {
                val prev = _viewState.value.keepSignedIn
                _viewState.value = _viewState.value.copy(keepSignedIn = !prev)

            }
            SignUpEvent.ChangeEmailAboutPricing -> {
                val prev = _viewState.value.emailAboutPricing
                _viewState.value = _viewState.value.copy(emailAboutPricing = !prev)
            }
        }
    }
}