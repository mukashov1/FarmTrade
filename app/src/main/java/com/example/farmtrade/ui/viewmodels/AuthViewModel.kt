package com.example.farmtrade.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.farmtrade.data.db.RequestState
import com.example.farmtrade.data.repository.DataStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class AuthViewModel(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    val name = mutableStateOf<String>("sdsds")
    val email = mutableStateOf<String>("")
    val lastname = mutableStateOf<String>("")
    val phoneNumber = mutableStateOf<String>("")
    val password = mutableStateOf<String>("")
    val login = mutableStateOf<String>("")

    val isPasswordVisible = mutableStateOf(false)
    val isLoginValid = mutableStateOf<Boolean>(false)

    //    fun registerUser(name: String, lastName: String, email: String, password: String, birthday: String, phone: String, token: String) {
//        viewModelScope.launch {
//            repository.saveUserRegistration(name, lastName, email, password, birthday, phone, token)
//            // Handle post-registration logic such as navigation
//        }
//    }
// StateFlow to handle UI state based on responses
    private val _registrationState = MutableStateFlow<RequestState>(RequestState.Idle)
    val registrationState = _registrationState.asStateFlow()


    fun setIsPasswordVisible() {
        isPasswordVisible.value = !isPasswordVisible.value
    }

}