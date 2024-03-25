    package com.example.farmtrade.data.repository

    import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.farmtrade.data.db.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

    val Context.dataStore by preferencesDataStore(name = "user_preferences")

    class DataStoreRepository(private val context: Context) {
        private val gson = Gson()


        companion object {
            val NAME_KEY = stringPreferencesKey("name")
            val LAST_NAME_KEY = stringPreferencesKey("last_name")
            val EMAIL_KEY = stringPreferencesKey("email")
            val PASSWORD_KEY = stringPreferencesKey("password")
            val BIRTHDAY_KEY = stringPreferencesKey("birthday")
            val PHONE_KEY = stringPreferencesKey("phone")
            val TOKEN_KEY = stringPreferencesKey("token")
            val CATALOG_ITEMS_KEY = stringPreferencesKey("catalog_items")
        }

        suspend fun saveUserRegistration(name: String, lastName: String, email: String, password: String, birthday: String, phone: String, token: String) {
            context.dataStore.edit { preferences ->
                preferences[NAME_KEY] = name
                preferences[LAST_NAME_KEY] = lastName
                preferences[EMAIL_KEY] = email
                preferences[PASSWORD_KEY] = password
                preferences[BIRTHDAY_KEY] = birthday
                preferences[PHONE_KEY] = phone
                preferences[TOKEN_KEY] = token
            }
        }

        val userName: Flow<String> = context.dataStore.data
            .map { preferences -> preferences[NAME_KEY] ?: "" }

        suspend fun saveCatalogItems(items: List<Product>) {
            val json = gson.toJson(items)
            context.dataStore.edit { preferences ->
                preferences[CATALOG_ITEMS_KEY] = json
            }
        }

        suspend fun getProductById(productId: String): Product? {
            return context.dataStore.data.map { preferences ->
                preferences[CATALOG_ITEMS_KEY]?.let { json ->
                    val type = object : TypeToken<List<Product>>() {}.type
                    gson.fromJson<List<Product>>(json, type).find { it.id == productId }
                }
            }.first() // Use first to get a single emission from the flow, converting it to a regular suspend function
        }


        val catalogItems: Flow<List<Product>> = context.dataStore.data
            .map { preferences ->
                preferences[CATALOG_ITEMS_KEY]?.let { json ->
                    val type = object : TypeToken<List<Product>>() {}.type
                    gson.fromJson(json, type)
                } ?: emptyList()
            }
    }