package com.example.farmtrade.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.farmtrade.data.db.User
import com.example.farmtrade.data.db.registration.RegistrationFormState
import com.example.farmtrade.data.db.registration.RegistrationUIEvent
import com.example.farmtrade.data.db.registration.RegistrationUIState
import com.example.farmtrade.data.db.registration.Validator
import com.example.farmtrade.data.repository.UserRepository
import com.example.farmtrade.ui.screens.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class RegistrationViewModel(
    private val navController: NavController,
    private val userRepository: UserRepository
) : ViewModel() {

    private val TAG = RegistrationViewModel::class.simpleName
    var registrationFormState = mutableStateOf(RegistrationFormState())

    private val _registrationUIState =
        mutableStateOf<RegistrationUIState>(RegistrationUIState.SignedOut)
    val registrationUIState: State<RegistrationUIState>
        get() = _registrationUIState

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: RegistrationUIEvent) {
        when (event) {
            is RegistrationUIEvent.FirstNameChanged -> {
                registrationFormState.value = registrationFormState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is RegistrationUIEvent.LastNameChanged -> {
                registrationFormState.value = registrationFormState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is RegistrationUIEvent.EmailChanged -> {
                registrationFormState.value = registrationFormState.value.copy(
                    email = event.email
                )
                printState()

            }


            is RegistrationUIEvent.PasswordChanged -> {
                registrationFormState.value = registrationFormState.value.copy(
                    password = event.password
                )
                printState()

            }

            is RegistrationUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is RegistrationUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationFormState.value = registrationFormState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }


    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = registrationFormState.value.email,
            password = registrationFormState.value.password
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationFormState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationFormState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationFormState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = registrationFormState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationFormState.value.privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationFormState.value = registrationFormState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )


        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }


    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationFormState.value.toString())
    }


    private fun createUserInFirebase(email: String, password: String) {
        _registrationUIState.value = RegistrationUIState.InProgress
        signUpInProgress.value = true

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                signUpInProgress.value = false
                if (task.isSuccessful) {
                    val userUID = task.result?.user?.uid ?: ""
                    val newUser = User(
                        userUID = userUID,
                        firstname = registrationFormState.value.firstName,
                        lastname = registrationFormState.value.lastName,
                        email = registrationFormState.value.email,
                        password = registrationFormState.value.password,
                    )
                    saveUserToRepository(newUser)
                    _registrationUIState.value = RegistrationUIState.SignIn
                    navController.navigate(Screen.Catalog.route)
                } else {
                    _registrationUIState.value = RegistrationUIState.Error
                    Log.e(TAG, "Failed to create user: ${task.exception?.message}")
                }
            }
    }

    private fun saveUserToRepository(newUser: User) {
        viewModelScope.launch {
            try {
                userRepository.addUser(newUser)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to save user to repository: ${e.message}")
            }
        }

    }
}