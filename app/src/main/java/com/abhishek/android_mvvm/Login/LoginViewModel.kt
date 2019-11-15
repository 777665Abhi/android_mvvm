package com.abhishek.android_mvvm.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abhishek.android_mvvm.Observer.ScreenState

class LoginViewModel(private val loginInteractor: LoginInteracter) : ViewModel(),
    LoginInteracter.LoginInteracterCallback {

    /**DECLARE LIVEDATA*/
    private val _loginState: MutableLiveData<ScreenState<LoginState>> = MutableLiveData()

    /**GETTER TO GET THE PRIVATE LD*/
    val loginState: LiveData<ScreenState<LoginState>>
        get() = _loginState

    /**ONCLICK PASS THE CALL TO INTERACTOR METHOD (FORWARD FLOW)*/
    fun onClickLogin(username: String, password: String) {
        _loginState.value = ScreenState.Loading
        loginInteractor.loginIn(username, password, this)
    }

    /**OVERRIDE METHODS OF INTERACTER (BACKWARD FLOW)*/
    override fun onErrorUser() {
        _loginState.value = ScreenState.Render(LoginState.ErrorUserame)
    }

    override fun onErrorPass() {
        _loginState.value = ScreenState.Render(LoginState.ErrorPassword)

    }

    override fun onSucces() {
        _loginState.value = ScreenState.Render(LoginState.Sucess)

    }

    override fun onServerError(msg: String) {
        _loginState.value = ScreenState.Render(LoginState.ErrorServer)
    }


}

class LoginViewModelFactory(private val loginInteractor: LoginInteracter) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginInteractor) as T
    }
}