package com.capstone.aipet.pref

import android.content.Context
import android.content.SharedPreferences

object UserPreference {
    fun init(context: Context, name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    private fun editorPreference(context: Context, name: String): SharedPreferences.Editor {
        val sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sharedPref.edit()
    }

    fun saveToken(token: String, context: Context) {
        val editor = editorPreference(context, "onSignIn")
        editor.putString("token", token)
        editor.apply()
    }

    fun logOut(context: Context) {
        val editor = editorPreference(context, "onSignIn")
        editor.remove("token")
        editor.remove("status")
        editor.apply()
    }
    fun saveUserData(username: String, name: String, address: String, phone: String, email: String, userId: Int, context: Context) {
        val editor = editorPreference(context, "onSignIn")
        editor.putString("name", name)
        editor.putString("address", address)
        editor.putString("username", username)
        editor.putString("phone", phone)
        editor.putString("email", email)
        editor.putInt("id", userId)
        editor.apply()
    }
}