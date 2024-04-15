package com.example.farmtrade.data.db.registration

sealed class RegistrationUIEvent{

    data class FirstNameChanged(val firstName:String) : RegistrationUIEvent()
    data class LastNameChanged(val lastName:String) : RegistrationUIEvent()
    data class EmailChanged(val email:String): RegistrationUIEvent()
    data class PasswordChanged(val password: String) : RegistrationUIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : RegistrationUIEvent()

    object RegisterButtonClicked : RegistrationUIEvent()
}