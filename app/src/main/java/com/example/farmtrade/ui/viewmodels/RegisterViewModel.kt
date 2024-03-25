package com.example.farmtrade.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.api.RegisterService
import com.example.farmtrade.data.api.RetrofitInstance
import com.example.farmtrade.data.db.RegisterErrorResponse
import com.example.farmtrade.data.db.RegisterSuccessResponse
import com.example.farmtrade.data.db.RequestState
import com.example.farmtrade.data.db.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response


class RegisterViewModel() : ViewModel() {


    //    fun registerUser(name: String, lastName: String, email: String, password: String, birthday: String, phone: String, token: String) {
//        viewModelScope.launch {
//            repository.saveUserRegistration(name, lastName, email, password, birthday, phone, token)
//            // Handle post-registration logic such as navigation
//        }
//    }
// StateFlow to handle UI state based on responses
    private val _registrationState = MutableStateFlow<RequestState>(RequestState.Idle)
    val registrationState = _registrationState.asStateFlow()

    // Function to register user
    fun registerUser() {
        val user =
            User("Bakdaulet", "Mukashov", "bahaaga@gmail.com", "baha", "2002-11-10", "+77768512002")
        viewModelScope.launch {
            try {
                _registrationState.value = RequestState.Loading
                val registerService = RetrofitInstance.retrofit.create(RegisterService::class.java)
                val response: Response<RegisterSuccessResponse> =
                    registerService.registerUser(user).execute()
                if (response.isSuccessful) {
                    // Handle the success
                    println("SUCCESS")
                    if (response.body() != null) {
                        response.body()?.let {
                            // Save tokens in a secure place, like EncryptedSharedPreferences
                            _registrationState.value = RequestState.Success
                            println(it.refreshToken)
                            println(it.accessToken)
                        }
                    } else {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            RegisterErrorResponse::class.java
                        )
                        _registrationState.value = RequestState.Error
                        println(errorResponse.message)

                    }
                } else {
                    // Parse the error body to get the ErrorResponse object
                    // Handle the error
                    println("FAILURE")
                }
            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()
                println("ERROR ${e.message}")
                println("ERROR ${e.cause}")
                _registrationState.value = RequestState.Error
                println("ERROR ${e.localizedMessage}")
            }
        }
    }

}