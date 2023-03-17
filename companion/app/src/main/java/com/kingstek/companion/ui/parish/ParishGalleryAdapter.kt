package com.kingstek.companion.ui.parish

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kingstek.companion.R
import com.kingstek.companion.models.parish.ParishGalleryModel
import com.kingstek.companion.onItemClickListener

class ParishGalleryAdapter(private val parishGalleryModel: List<ParishGalleryModel>, private var mListner: onItemClickListener) : RecyclerView.Adapter<ParishGalleryAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParishGalleryAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.gallery_cardview, parent, false)
        return ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: ParishGalleryAdapter.ViewHolder, position: Int) {
       val event = parishGalleryModel[position]

        val eventTitle = holder.eventTitle
        eventTitle.text = event.eventTitle

        val eventHeading = holder.eventHeading
        eventHeading.text = event.eventDescription

        val eventImageOne = holder.eventImageOne
        event.eventImages?.get(1)?.eventImage.let { eventImageOne.setImageResource(it!!) }

        val eventImageTwo = holder.eventImageTwo
        event.eventImages?.get(2)?.eventImage.let { eventImageTwo.setImageResource(it!!) }

        val eventImageThree = holder.eventImageThree
        event.eventImages?.get(3)?.eventImage.let { eventImageThree.setImageResource(it!!) }
    }

    override fun getItemCount(): Int {
        return parishGalleryModel.size
    }

    class ViewHolder (itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val eventTitle = itemView.findViewById<TextView>(R.id.tv_gallery_title)
        val eventImageOne = itemView.findViewById<ImageView>(R.id.img_image_one)
        val eventImageTwo = itemView.findViewById<ImageView>(R.id.img_image_two)
        val eventImageThree = itemView.findViewById<ImageView>(R.id.img_image_three)
        val eventHeading = itemView.findViewById<TextView>(R.id.tv_gallery_heading)

        init {
            itemView.setOnClickListener{

                listener.onItemClicked(absoluteAdapterPosition, it)
            }
        }
    }
}