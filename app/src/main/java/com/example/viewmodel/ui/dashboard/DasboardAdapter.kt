package com.example.viewmodel.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewModel.R
import com.example.viewmodel.retrofit.models.Users
import kotlinx.android.synthetic.main.item_row_load.view.*

class DasboardAdapter(private val context: Context) :RecyclerView.Adapter<DasboardAdapter.DasViewHolder>() {
private var dataList = mutableListOf<Users>()

    fun setListUser(data:MutableList<Users>){
        dataList=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DasViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_row_load,parent,false)
        return DasViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataList.size >0){
            return dataList.size
        }else
            return 0
    }

    override fun onBindViewHolder(holder: DasViewHolder, position: Int) {
        val user:Users =dataList[position]
        holder.bindVieW(user)
    }
    inner class DasViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
              fun bindVieW(user: Users){
                  if (context != null) {
                      Glide.with(context).load(user.imagen).into(itemView.profile_image)
                  }
                  itemView.tvwNameUser.text= user.name
                  itemView.tvwEmailUser.text = user.mail

            }
    }
}