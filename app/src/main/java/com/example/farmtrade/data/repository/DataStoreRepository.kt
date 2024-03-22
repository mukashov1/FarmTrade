    package com.example.farmtrade.data.repository

    import android.content.Context
    import androidx.datastore.core.DataStore
    import androidx.datastore.preferences.core.Preferences
    import androidx.datastore.preferences.core.edit
    import androidx.datastore.preferences.core.stringPreferencesKey
    import androidx.datastore.preferences.preferencesDataStore
    import com.example.farmtrade.data.db.Product
    import com.google.gson.Gson
    import com.google.gson.reflect.TypeToken
    import kotlinx.coroutines.flow.Flow
    import kotlinx.coroutines.flow.first
    import kotlinx.coroutines.flow.map


    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "catalog_items")


    class CatalogDataStoreRepository(private val context: Context) {
        private val gson = Gson()


        companion object {
            val CATALOG_ITEMS_KEY = stringPreferencesKey("catalog_items")
        }


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