package com.example.tiktok.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiktok.databinding.ItemPagerBinding
import com.example.tiktok.models.MyReel

class MyViewPagerAdapter(var list: ArrayList<MyReel> = ArrayList()): RecyclerView.Adapter<MyViewPagerAdapter.Vh>() {
    inner class Vh(val itemPagerBinding: ItemPagerBinding): RecyclerView.ViewHolder(itemPagerBinding.root){
        fun onBind(MyReel: MyReel){
            itemPagerBinding.tvItem.text = MyReel.title
            itemPagerBinding.itemVideoView.setVideoURI(Uri.parse(MyReel.videoLink))
            itemPagerBinding.itemVideoView.start()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}