package com.abhishek.android_mvvm.Login

import android.content.Context

class LoginInteracter(private val context: Context) {


    fun loginIn(username: String, password: String, listener: LoginInteracterCallback) {
        when {
            username.isEmpty() -> listener.onErrorUser()
            password.isEmpty() -> listener.onErrorPass()
            else -> processLoginRequest(username, password, listener)
        }
    }

    fun processLoginRequest(username: String, password: String, listener: LoginInteracterCallback) {
        if (username == "abhi") {
            if (password == "1234") {
                listener.onSucces()
            } else {
                listener.onServerError("Password not correct")
            }
        } else {
            listener.onServerError("User not exit")
        }
    }


    interface LoginInteracterCallback {
        fun onErrorUser()
        fun onErrorPass()
        fun onSucces()
        fun onServerError(msg: String)
    }
}