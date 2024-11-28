package com.example.learnconnect.data.local.preferences

import android.content.Context
import com.example.learnconnect.domain.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserPreferences(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    suspend fun saveUser(user: User) {
        withContext(Dispatchers.IO) {
            prefs.edit()
                .putString(KEY_USER, gson.toJson(user))
                .apply()
        }
    }

    suspend fun getUser(): User? {
        return withContext(Dispatchers.IO) {
            prefs.getString(KEY_USER, null)?.let {
                gson.fromJson(it, User::class.java)
            }
        }
    }

    suspend fun clearUser() {
        withContext(Dispatchers.IO) {
            prefs.edit().remove(KEY_USER).apply()
        }
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        withContext(Dispatchers.IO) {
            prefs.edit()
                .putBoolean(KEY_LOGGED_IN, isLoggedIn)
                .apply()
        }
    }

    suspend fun isLoggedIn(): Boolean {
        return withContext(Dispatchers.IO) {
            prefs.getBoolean(KEY_LOGGED_IN, false)
        }
    }

    companion object {
        private const val PREFS_NAME = "learn_connect_prefs"
        private const val KEY_USER = "user"
        private const val KEY_LOGGED_IN = "logged_in"
    }
}