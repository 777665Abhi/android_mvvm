package com.abhishek.android_mvvm.home

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.abhishek.android_mvvm.R
import com.abhishek.android_mvvm.network.respones.ResponsePost

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    /**1 Need to pass data in constructor*/
    private var context: Context? = null
    private var postList: ArrayList<ResponsePost>

    constructor(
        context: Context,
        farmerList: ArrayList<ResponsePost>
    ) : super() {
        this.context = context
        this.postList = farmerList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_post_adapter,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return  postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPostTittle.text=postList[position].tittle.toString()
        holder.tvPost.text=postList[position].body.toString()
        holder.tvPostDetail.text="Post by user :"+postList[position].userId.toString()


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvPostTittle = view.findViewById<TextView>(R.id.tvPostTittle)
        var tvPost = view.findViewById<TextView>(R.id.tvPost)
        var tvPostDetail = view.findViewById<TextView>(R.id.tvPostDetail)
    }
}