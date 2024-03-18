package com.example.farmtrade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.farmtrade.R
import com.example.farmtrade.ui.viewmodels.UserViewModel

@Composable
fun RegistrationScreen(navController: NavController, viewModel: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    val isNameValid = isValidCyrillic(name) || name.isEmpty()
    val isSurnameValid = isValidCyrillic(surname) || surname.isEmpty()
    val isPhoneValid =
        phone.length == 10

    val isButtonEnabled = isNameValid && isSurnameValid && isPhoneValid && name.isNotEmpty() && surname.isNotEmpty() && phone.isNotEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Вход",
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
            NameTextField(name = name, onNameChange = { name = it }, isError = !isNameValid)
            Spacer(modifier = Modifier.height(8.dp))
            SurnameTextField(
                surname = surname,
                onSurnameChange = { surname = it },
                isError = !isSurnameValid
            )
            Spacer(modifier = Modifier.height(8.dp))
            PhoneTextField(
                phone = phone,
                onPhoneChange = { if (it.length <= 10) phone = it },
                isError = phone.isNotEmpty() && !isPhoneValid,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SignInButton(isEnabled = isButtonEnabled, onClick = {
//                viewModel.insertUser(User(name = name, surname = surname, phone = phone, isSigned = true))

//                println("BUTTON CLICKED")
                // Navigate to the catalog screen
                navController.navigate("home") {
                    // Clear back stack
//                    popUpTo("registration") { inclusive = true }
                }
            })
        }
        Text(
            buildAnnotatedString {
                append("Нажимая кнопку \"Войти\", Вы принимаете \n")
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("условия пользовательского соглашения")
                }
            },
            color = colorResource(id = R.color.grey),
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField(name: String, onNameChange: (String) -> Unit, isError: Boolean) {

    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        singleLine = true,
        isError = isError,
        placeholder = { Text(text = "Имя") },
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
            if (name.isNotEmpty()) {
                IconButton(onClick = { onNameChange("") }) {
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
                IconButton(onClick = { onPhoneChange("")}) {
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
        enabled = isEnabled
    ) {
        Text("Войти")
    }
}