package com.bangkit.dewpet.data.preferences

import android.content.Context

internal class UserPref (context: Context) {
    companion object {
        const val AUTH_USER = "user_pref"
        const val TOKEN = "token"
        const val NAME = "name"
    }

    val preferences = context.getSharedPreferences(AUTH_USER, Context.MODE_PRIVATE)

    fun setToken(value: UserModel){
        val prefEditor = preferences.edit()
        prefEditor.putString(TOKEN, value.token)
        prefEditor.putString(NAME, value.name)
        prefEditor.apply()
    }

    fun getToken(): UserModel{
        val model = UserModel()
        model.token = preferences.getString(TOKEN, "")
        model.name = preferences.getString(NAME, "")
        return model
    }
}