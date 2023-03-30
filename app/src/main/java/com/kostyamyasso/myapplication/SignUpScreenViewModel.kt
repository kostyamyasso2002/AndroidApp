package com.kostyamyasso.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

class SignUpScreenViewModel : ViewModel() {
    private val _viewState: MutableLiveData<SignUpState> = MutableLiveData(SignUpState())
    val viewState: LiveData<SignUpState> = _viewState

    fun obtainEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.ChangeName -> {
                _viewState.postValue(_viewState.value?.copy(userName = event.newName))
            }
            is SignUpEvent.ChangeEmail -> {
                _viewState.postValue(_viewState.value?.copy(email = event.newEmail))

            }
            is SignUpEvent.ChangePassword -> {
                _viewState.postValue(_viewState.value?.copy(password = event.newPassword))

            }
            SignUpEvent.ChangePasswordVisibility -> {
                val prev = _viewState.value?.passwordVisible
                _viewState.postValue(prev?.let { _viewState.value?.copy(passwordVisible = !it) })
            }
            SignUpEvent.ChangeKeepSignedIn -> {
                val prev = _viewState.value?.keepSignedIn
                _viewState.postValue(prev?.let { _viewState.value?.copy(keepSignedIn = !it) })

            }
            SignUpEvent.ChangeEmailAboutPricing -> {
                val prev = _viewState.value?.emailAboutPricing
                _viewState.postValue(prev?.let { _viewState.value?.copy(emailAboutPricing = !it) })
            }
        }
    }
}