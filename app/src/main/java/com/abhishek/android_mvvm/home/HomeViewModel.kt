package com.abhishek.android_mvvm.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abhishek.android_mvvm.Observer.ScreenState
import com.abhishek.android_mvvm.network.respones.ResponsePost
import retrofit2.Response

class HomeViewModel(private val homeInteractor: HomeInteracter) : ViewModel(),
    HomeInteracter.HomeInteracterCallback {

    private val _postState: MutableLiveData<ScreenState<HomeState>> = MutableLiveData()
    var postList:MutableLiveData<List<ResponsePost>> = MutableLiveData()

    /*Declare public Getter for LD*/
    val homeState: LiveData<ScreenState<HomeState>>
        get() = _postState

    fun postsApiHit() {
        homeInteractor.postApiHitInt(this)
    }

    override fun apiSuccess(response: Response<ArrayList<ResponsePost>>) {
//        postList.value=response
        _postState.value = ScreenState.Render(HomeState.apiSuccess)
    }

    override fun apiError() {
        _postState.value = ScreenState.Render(HomeState.apiError)
    }

    class HomeViewModelFactory(private val loginInteractor: HomeInteracter) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(loginInteractor) as T
        }
    }
}