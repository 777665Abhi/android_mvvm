package com.abhishek.android_mvvm.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.abhishek.android_mvvm.home.HomeActivity

class BasicFunction {
    companion object{
        fun moveNextScreen(source:Context,  destination:Class<*>)
        {
            val intent = Intent(source, destination)
            source.startActivity(intent)
        }

        fun showToast(context:Context,msg:String)
        {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }
        fun showProgress(view:View)
        {
            view.visibility=View.VISIBLE
        }
        fun hideProgress(view:View)
        {
            view.visibility=View.GONE
        }
    }
}