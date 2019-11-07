package com.abhishek.android_mvvm.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.abhishek.android_mvvm.Observer.ScreenState
import com.abhishek.android_mvvm.R
import com.abhishek.android_mvvm.network.respones.ResponsePost
import com.abhishek.android_mvvm.utils.BasicFunction
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    var homeViewModel: HomeViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel = ViewModelProviders.of(
            this,
            HomeViewModel.HomeViewModelFactory(HomeInteracter(this))
        )[HomeViewModel::class.java]
        homeViewModel!!.homeState.observe(::getLifecycle, ::updateHomeUI)
        /*Call for api hit to get post*/
        homeViewModel!!.postsApiHit()
    }

    fun updateHomeUI(screenState: ScreenState<HomeState>?) {
        when (screenState) {
            ScreenState.Loading -> BasicFunction.showProgress(progress)
            is ScreenState.Render -> processHomeUI(screenState.renderState)
        }
    }

    fun processHomeUI(homeState: HomeState) {
        when (homeState) {
            HomeState.apiError -> {
                BasicFunction.hideProgress(progress)
                BasicFunction.showToast(this, "Error")
            }
            HomeState.apiSuccess -> {
                BasicFunction.hideProgress(progress)
                BasicFunction.showToast(this, "Success")
            }
        }
    }
}
