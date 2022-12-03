package com.kingstek.companion.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.kingstek.companion.R
import com.kingstek.companion.dummy_data.NewsModel

class NewsAdapter(private val newsModel: MutableLiveData<List<NewsModel>>, private var mListner: onItemClickListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.news_cardview, parent, false)
        return ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val news = newsModel.value?.get(position)

        val headline = holder.newsHeadline
        headline.text = news?.headline

        val detail = holder.newsdetail
        detail.text = news?.detail

        val image = holder.newsImage
        news?.headlineImage?.let { image.setImageResource(it) }

    }

    override fun getItemCount(): Int {
       return newsModel.value?.size as Int
    }

    inner class ViewHolder (itemView : View, listner: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val newsHeadline = itemView.findViewById<TextView>(R.id.news_headline)
        val newsdetail = itemView.findViewById<TextView>(R.id.news_details)
        val newsImage = itemView.findViewById<ImageView>(R.id.news_image)

        init {
            itemView.setOnClickListener{

                listner.onItemClicked(absoluteAdapterPosition, it)
            }
        }
    }


}