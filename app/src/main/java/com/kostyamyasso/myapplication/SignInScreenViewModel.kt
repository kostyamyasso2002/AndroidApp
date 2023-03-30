package com.kostyamyasso.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class SignInEvent {
    data class ChangeEmail(val newEmail: String) : SignInEvent()
    data class ChangePassword(val newPassword: String) : SignInEvent()
    object ChangePasswordVisibility : SignInEvent()
}

data class SignInState(
    var userName: String = "",
    var email: String = "",
    var password: String = "",
    var passwordVisible: Boolean = false,
    var keepSignedIn: Boolean = true,
    var emailAboutPricing: Boolean = true
)

class SignInScreenViewModel : ViewModel() {
    private val _viewState: MutableLiveData<SignInState> = MutableLiveData(SignInState())
    val viewState: LiveData<SignInState> = _viewState

    fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.ChangeEmail -> {
                _viewState.postValue(_viewState.value?.copy(email = event.newEmail))

            }
            is SignInEvent.ChangePassword -> {
                _viewState.postValue(_viewState.value?.copy(password = event.newPassword))

            }
            SignInEvent.ChangePasswordVisibility -> {
                val prev = _viewState.value?.passwordVisible
                _viewState.postValue(prev?.let { _viewState.value?.copy(passwordVisible = !it) })
            }
        }
    }
}