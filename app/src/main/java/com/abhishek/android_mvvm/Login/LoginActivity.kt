package com.abhishek.android_mvvm.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.abhishek.android_mvvm.Observer.ScreenState
import com.abhishek.android_mvvm.R
import com.abhishek.android_mvvm.home.HomeActivity
import com.abhishek.android_mvvm.utils.BasicFunction
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    var loginVM: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**1 INSTANCE OF VM*/
        loginVM = ViewModelProviders.of(
            this,
            LoginViewModelFactory(LoginInteracter(this))
        )[LoginViewModel::class.java]

        /**2 OBSERVING THE STATE OF LOGIN*/
        loginVM!!.loginState.observe(::getLifecycle, ::updateUI)
        bnLogin.setOnClickListener { onClick() }

    }

    /***CALL THE CLICK METHOD OF VM ONCLICK OF BUTTON*/
    fun onClick() {
        loginVM!!.onClickLogin(etUser.text.toString(), etPass.text.toString())
    }

    /**ACTIONS CORRESPONDING TO STATE*/
    private fun updateUI(screenState: ScreenState<LoginState>?) {
        when (screenState) {
            ScreenState.Loading -> progress.visibility = View.VISIBLE
            is ScreenState.Render -> processLoginState(screenState.renderState)
        }
    }

    private fun processLoginState(renderState: LoginState) {
        when (renderState) {
            LoginState.ErrorPassword -> etPass.error = "Password error"
            LoginState.ErrorUserame -> etUser.error = "Username error"
            LoginState.ErrorServer -> Toast.makeText(
                this,
                "Error to login",
                Toast.LENGTH_SHORT
            ).show()
            LoginState.Sucess -> {
                BasicFunction.moveNextScreen(this, HomeActivity::class.java)
            }
        }
    }


}
