package com.kingstek.companion.ui.parish

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kingstek.companion.R
import com.kingstek.companion.dummy_data.ParishModel
import com.kingstek.companion.models.parish.Parish
import com.kingstek.companion.onItemClickListener

class ParishAdapter(private val parishModel: List<Parish>, private var mListner: onItemClickListener, private val context: Context) : RecyclerView.Adapter<ParishAdapter.ViewHolder> () {

//    fun setOnclickListener(listner: onItemClickListener) {
//        mListner = listner
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParishAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.parish_cardview, parent, false)
        return ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: ParishAdapter.ViewHolder, position: Int) {
        val parish = parishModel[position]

        val parishName = holder.parishName
        parishName.text = parish.parishName

        val address = holder.address
        address.text = parish.address

        val deanery = holder.deanery
        deanery.text = parish.deanery

        val diocese = holder.diocese
        diocese.text = parish.diocese

        //Todo find a way to identify main parish image to use on recycler view
        val image = holder.image
        parish.parishImage?.get(1)?.let {
            Glide.with(context)
                .load(it)
                .apply(RequestOptions().centerCrop())
                .into(image)
        }

    }

    override fun getItemCount(): Int {
       return parishModel.size as Int
    }

    inner class ViewHolder (itemView : View, listner: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val parishName: TextView = itemView.findViewById<TextView>(R.id.tv_parish_name)
        val address = itemView.findViewById<TextView>(R.id.tv_parish_address)
        val deanery = itemView.findViewById<TextView>(R.id.tv_deanery)
        val diocese = itemView.findViewById<TextView>(R.id.tv_diocese)
        val image = itemView.findViewById<ImageView>(R.id.iv_news_image)

        init {
            itemView.setOnClickListener{

                listner.onItemClicked(absoluteAdapterPosition, it)
            }
        }
    }


}