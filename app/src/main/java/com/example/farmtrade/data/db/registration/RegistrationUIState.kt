package com.example.farmtrade.data.db.registration

sealed class RegistrationUIState {
    object SignedOut : RegistrationUIState()
    object InProgress : RegistrationUIState()
    object Error : RegistrationUIState()
    object SignIn : RegistrationUIState()
}