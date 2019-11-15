package com.abhishek.android_mvvm.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.android_mvvm.Observer.ScreenState
import com.abhishek.android_mvvm.R
import com.abhishek.android_mvvm.network.respones.ResponsePost
import com.abhishek.android_mvvm.utils.BasicFunction
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    var homeViewModel: HomeViewModel? = null
    var mAdapter : HomeAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /**INSTANCE OF VM*/
        homeViewModel = ViewModelProviders.of(
            this,
            HomeViewModel.HomeViewModelFactory(HomeInteracter(this))
        )[HomeViewModel::class.java]

        /**OBSERVE THE VM STATE*/
        homeViewModel!!.homeState.observe(::getLifecycle, ::updateHomeUI)

        initView()
    }

    fun initView() {
        /**Call for api hit to get post VM METHOD*/
        homeViewModel!!.postsApiHit()
        rvHome!!.layoutManager = LinearLayoutManager(this)

        /**Click Listener*/
        flAdd.setOnClickListener(this)
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
                if(homeViewModel!!.postList.value!! !=null)
                {
               mAdapter = HomeAdapter(
                    this,homeViewModel!!.postList.value!!
                    )
                rvHome.adapter=mAdapter}
               // rvHome.adapter = homeViewModel!!.postAdapter.value
                BasicFunction.showToast(this, "Success")
            }
            HomeState.updateList -> {
                mAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.flAdd->{
                homeViewModel!!.addItem()
           }
        }
    }

}
