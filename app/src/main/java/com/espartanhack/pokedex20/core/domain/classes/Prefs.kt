package com.espartanhack.pokedex20.core.domain.classes

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class that manages the preferences of the app.
 *
 * @param appContext The application context.
 *
 * @property saveString Function to save a string in the preferences.
 * @property getString Function to get a string from the preferences.
 *
 * @property saveBool Function to save a boolean in the preferences.
 * @property getBool Function to get a boolean from the preferences.
 */
@Singleton
class Prefs @Inject constructor(
    @ApplicationContext private val appContext: Context
) {
    private val Context.dataStore by preferencesDataStore(PREFS_NAME)

    /**
     * Function that saves a boolean in the preferences.
     *
     * @param key The key of the preference.
     * @param value The value to put in the preference.
     */
    suspend fun saveBool(key: String, value: Boolean) {
        appContext.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    fun getBool(key: String, defaultValue: Boolean = false) = appContext.dataStore.data.map { preferences ->
        preferences[booleanPreferencesKey(key)] ?: defaultValue
    }

    /**
     * The keys of the preferences and his default values.
     */
    companion object {
        private const val PREFS_NAME = "PokePrefs"

        const val INITIAL_SETUP = "initial_setup"
        const val INITIAL_SETUP_DEFAULT = false
    }
}