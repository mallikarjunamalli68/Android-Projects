package com.example.advancedloginandsignup.pages

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("session_prefs")

class SessionManager(private val context: Context){
    companion object {
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map {
        preferences -> preferences[IS_LOGGED_IN] ?: false
    }
    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit {
            preferences ->
            preferences[IS_LOGGED_IN] = value
        }
    }
}
