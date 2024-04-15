package com.example.farmtrade.ui.screens

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.farmtrade.R
import com.example.farmtrade.data.db.registration.RegistrationUIEvent
import com.example.farmtrade.data.db.registration.RegistrationUIState
import com.example.farmtrade.ui.components.ButtonComponent
import com.example.farmtrade.ui.components.CheckboxComponent
import com.example.farmtrade.ui.components.ClickableLoginTextComponent
import com.example.farmtrade.ui.components.DividerTextComponent
import com.example.farmtrade.ui.components.HeadingTextComponent
import com.example.farmtrade.ui.components.MyTextFieldComponent
import com.example.farmtrade.ui.components.NormalTextComponent
import com.example.farmtrade.ui.components.PasswordTextFieldComponent
import com.example.farmtrade.ui.viewmodels.RegistrationViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RegistrationScreen(
    navController: NavController,
    registrationViewModel: RegistrationViewModel = viewModel()
) {
    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (registrationViewModel.registrationUIState.value) {
            is RegistrationUIState.SignedOut -> println("HHOOEE")// Display signed out UI components
            is RegistrationUIState.InProgress -> CircularProgressIndicator()
            is RegistrationUIState.Error -> {
                Log.e(
                    ContentValues.TAG,
                    "${registrationViewModel.registrationUIState.value}"
                )
                Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show()
            }

            // Using the SignIn state as a trigger to navigate
            is RegistrationUIState.SignIn -> {
                println("DLFKJDLFK NAVIGATION")
                navController.navigate(Screen.Catalog.route)}
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

                NormalTextComponent(value = "Hello")
                HeadingTextComponent(value = "Create account")
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = "First Name",
                    painterResource(id = R.drawable.eye_active),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegistrationUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = registrationViewModel.registrationFormState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = "Last Name",
                    painterResource = painterResource(id = R.drawable.account),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegistrationUIEvent.LastNameChanged(it))
                    },
                    errorStatus = registrationViewModel.registrationFormState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = "Email",
                    painterResource = rememberVectorPainter(image = Icons.Filled.Email),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegistrationUIEvent.EmailChanged(it))
                    },
                    errorStatus = registrationViewModel.registrationFormState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = "Password",
                    painterResource = painterResource(id = R.drawable.eye_default),
                    onTextSelected = {
                        registrationViewModel.onEvent(RegistrationUIEvent.PasswordChanged(it))
                    },
                    errorStatus = registrationViewModel.registrationFormState.value.passwordError
                )

                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
//                        PostOfficeAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    },
                    onCheckedChange = {
                        registrationViewModel.onEvent(
                            RegistrationUIEvent.PrivacyPolicyCheckBoxClicked(
                                it
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
//                        registrationViewModel.onEvent(RegistrationUIEvent.RegisterButtonClicked)
                        navController.navigate(Screen.Catalog.route)
                    },
//                    isEnabled = registrationViewModel.allValidationsPassed.value
                    isEnabled = true

                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.navigate("loginScreen")
                })
            }

        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    RegistrationScreen(navController = rememberNavController())
}