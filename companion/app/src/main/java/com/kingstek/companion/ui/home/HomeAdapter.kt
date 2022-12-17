package com.kingstek.companion.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.kingstek.companion.R
import com.kingstek.companion.dummy_data.HomeModel
import com.kingstek.companion.onItemClickListener

class HomeAdapter(private val homeModel: MutableLiveData<List<HomeModel>>, private var mListener: onItemClickListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.home_cardview, parent, false)
        return ViewHolder(view, mListener)    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val home = homeModel.value?.get(position)

        val homeTitle = holder.homeHeading
        homeTitle.text = home?.homeText

        val homeImage = holder.homeImage
        home?.homeImage?.let {homeImage.setImageResource(it)}

        val homeDetails = holder.homeDetails
        homeDetails.text = home?.homeDetails
    }

    override fun getItemCount(): Int {
        return homeModel.value?.size as Int
    }

    inner class ViewHolder (itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val homeHeading = itemView.findViewById<TextView>(R.id.home_title)
        val homeImage = itemView.findViewById<ImageView>(R.id.home_image)
        val homeDetails = itemView.findViewById<TextView>(R.id.home_details)

        init {
            itemView.setOnClickListener{
                listener.onItemClicked(absoluteAdapterPosition, it)
            }
        }
    }

}