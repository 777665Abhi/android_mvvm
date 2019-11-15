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


    /**LD INSTANCE*/
    private val _postState: MutableLiveData<ScreenState<HomeState>> = MutableLiveData()
    private val _postList: MutableLiveData<ArrayList<ResponsePost>> = MutableLiveData()
//    private val _postAdapter: MutableLiveData<HomeAdapter> = MutableLiveData()


    /**Declare public Getter for LD*/
    val homeState: LiveData<ScreenState<HomeState>>
        get() = _postState
    val postList: LiveData<ArrayList<ResponsePost>>
        get() = _postList

//    val postAdapter: LiveData<HomeAdapter>
//        get() = _postAdapter

    fun postsApiHit() {
        homeInteractor.postApiHitInt(this)
    }

    fun addItem()
    {
        homeInteractor.addItem(this)

    }
    override fun apiSuccess(postList: ArrayList<ResponsePost>) {
        _postList.value=postList
        _postState.value = ScreenState.Render(HomeState.apiSuccess)
    }

    override fun apiError() {
        _postState.value = ScreenState.Render(HomeState.apiError)
    }

    override fun addItem(addItem: ResponsePost) {
        _postList.value!!.add(addItem)
        _postState.value = ScreenState.Render(HomeState.updateList)
    }

    class HomeViewModelFactory(private val loginInteractor: HomeInteracter) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(loginInteractor) as T
        }
    }
}