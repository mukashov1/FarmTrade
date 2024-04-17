package com.example.farmtrade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.farmtrade.R
import com.example.farmtrade.data.repository.DataStoreRepository
import com.example.farmtrade.ui.viewmodels.LoginViewModel

@Composable
fun LogInScreen(navController: NavController) {
    println("LOGIN")
    val dataStoreRepository = DataStoreRepository(LocalContext.current)
    val viewModel: LoginViewModel = viewModel()
    val email by viewModel.email

    val isPasswordVisible by remember {
        viewModel.isPasswordVisible
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Sign In",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            LoginTextField(
                login = email,
                onLoginChanged = { viewModel.email.value = it },
                isError = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordTextField(
                password = viewModel.password.value,
                onPasswordChanged = { viewModel.password.value = it },
                onTrailedIconClicked = { viewModel.isPasswordVisible.value = !viewModel.isPasswordVisible.value },
                isPasswordVisible = isPasswordVisible,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SignInButton(isEnabled = true, onClick = {
                viewModel.signIn()
//                    navController.navigate(Screen.Profile.route)
            }
            )
        }
        ClickableRegisterText(
            onClick = { println("Navigate to Registration")
                      navController.navigate("registrationScreen")}, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    login: String,
    onLoginChanged: (String) -> Unit,
    isError: Boolean
) {

    OutlinedTextField(
        value = login,
        onValueChange = onLoginChanged,
        singleLine = true,
        isError = isError,
        placeholder = { Text(text = "Email") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colorResource(id = R.color.light_grey),
            unfocusedBorderColor = colorResource(id = R.color.light_grey),
            focusedBorderColor = colorResource(id = R.color.grey),
            placeholderColor = colorResource(id = R.color.grey),
            cursorColor = MaterialTheme.colorScheme.onSurface,
            errorBorderColor = colorResource(id = R.color.orange),
            errorCursorColor = colorResource(id = R.color.orange),
            errorTrailingIconColor = colorResource(id = R.color.orange)
        ),
        trailingIcon = {
            if (login.isNotEmpty()) {
                IconButton(onClick = { onLoginChanged("") }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear text"
                    )
                }
            }
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions.Default
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    onTrailedIconClicked: () -> Unit,
    isPasswordVisible: Boolean = false,
) {

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChanged,
        singleLine = true,
        placeholder = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colorResource(id = R.color.light_grey),
            unfocusedBorderColor = colorResource(id = R.color.light_grey),
            focusedBorderColor = colorResource(id = R.color.grey),
            placeholderColor = colorResource(id = R.color.grey),
            cursorColor = MaterialTheme.colorScheme.onSurface,
            errorBorderColor = colorResource(id = R.color.orange),
            errorCursorColor = colorResource(id = R.color.orange),
            errorTrailingIconColor = colorResource(id = R.color.orange)
        ),
        trailingIcon = {
            IconButton(onClick = onTrailedIconClicked) {
                Icon(
                    painterResource(
                        id = if (isPasswordVisible) R.drawable.eye_active
                        else R.drawable.eye_default
                    ),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Clear text"
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions.Default,
        visualTransformation =
        if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurnameTextField(surname: String, onSurnameChange: (String) -> Unit, isError: Boolean) {

    OutlinedTextField(
        value = surname,
        onValueChange = onSurnameChange,
        singleLine = true,
        isError = isError,
        placeholder = { Text(text = "Фамилия") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colorResource(id = R.color.light_grey),
            unfocusedBorderColor = colorResource(id = R.color.light_grey),
            focusedBorderColor = colorResource(id = R.color.grey),
            placeholderColor = colorResource(id = R.color.grey),
            cursorColor = MaterialTheme.colorScheme.onSurface,
            errorBorderColor = colorResource(id = R.color.orange),
            errorCursorColor = colorResource(id = R.color.orange),
            errorTrailingIconColor = colorResource(id = R.color.orange)
        ),
        trailingIcon = {
            if (surname.isNotEmpty()) {
                IconButton(onClick = { onSurnameChange("") }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear text"
                    )
                }
            }
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions.Default
    )
}

fun isValidCyrillic(input: String): Boolean {
    val cyrillicPattern = Regex(pattern = "^[а-яА-ЯёЁ]+$")
    return input.isEmpty() || input.matches(cyrillicPattern)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneTextField(phone: String, onPhoneChange: (String) -> Unit, isError: Boolean) {
    var isFocused by remember { mutableStateOf(false) }

    val phoneVisualTransformation = VisualTransformation { text ->
        val builder = StringBuilder("+7 ")
        text.text.forEachIndexed { index, c ->
            when (index) {
                0, 1, 2, 4, 5, 7, 9 -> builder.append(c)
                3, 6, 8 -> builder.append(" $c")
            }
        }
        val formattedString = builder.toString()
        val annotatedString = AnnotatedString(formattedString)
        TransformedText(annotatedString, object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = formattedString.length
            override fun transformedToOriginal(offset: Int): Int = text.length
        })
    }
    OutlinedTextField(
        value = phone,
        onValueChange = onPhoneChange,
        visualTransformation = if (isFocused || phone.isNotEmpty()) phoneVisualTransformation else VisualTransformation.None,
        singleLine = true,
        isError = isError,
        placeholder = { Text(text = "Номер Телефона") },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> isFocused = focusState.isFocused },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colorResource(id = R.color.light_grey),
            unfocusedBorderColor = colorResource(id = R.color.light_grey),
            focusedBorderColor = colorResource(id = R.color.grey),
            placeholderColor = colorResource(id = R.color.grey),
            cursorColor = MaterialTheme.colorScheme.onSurface,
            errorBorderColor = colorResource(id = R.color.orange),
            errorCursorColor = colorResource(id = R.color.orange),
            errorTrailingIconColor = colorResource(id = R.color.orange)
        ),
        trailingIcon = {
            if (phone.isNotEmpty()) {
                IconButton(onClick = { onPhoneChange("") }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear text"
                    )
                }
            }
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
    )
}

@Composable
fun ClickableRegisterText(onClick: () -> Unit, modifier: Modifier = Modifier) {
    val annotatedText = buildAnnotatedString {
        append("Haven't we seen you around here before?   ")
        pushStringAnnotation("Click", "Register")
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline,
                color = colorResource(id = R.color.grey),
                fontSize = 16.sp,
            )
        ) {
            append("Register")
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            val annotations = annotatedText.getStringAnnotations("Click", offset, offset)
            if (annotations.isNotEmpty()) {
                onClick()
            }
        },
        modifier = modifier
    )
}

@Composable
fun SignInButton(isEnabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = colorResource(id = R.color.light_pink),
            containerColor = colorResource(id = R.color.pink),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled = true
    ) {
        Text("Sign In")
    }
}

suspend fun saveLoginState(isLoggedIn: Boolean, dataStoreRepository: DataStoreRepository) {
    dataStoreRepository.saveLoginState(isLoggedIn)
}