package com.kanish.gnewscl.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kanish.gnewscl.R
import com.kanish.gnewscl.data.entity.NewsResult

class NewsListAdapter(var itemList:List<NewsResult>):RecyclerView.Adapter<NewsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
      return NewsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false))
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
         if (position< itemList.size){
             holder.bind(itemList[position])
         }
    }

    override fun getItemCount(): Int {
      return itemList.size
    }
}

class NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
     private val titleText: TextView=itemView.findViewById(R.id.tvNewsTitle)
    private val detail: TextView=itemView.findViewById(R.id.tvNewsDetail)
    private val img:ImageView = itemView.findViewById(R.id.imgNews)
    fun bind( data : NewsResult){
    Glide.with(itemView.context).load(data.image_url).into(img)
        titleText.text= data.title
        detail.text=data.description
    }


}
