package com.kingstek.companion.ui.parish.updates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.kingstek.companion.R
import com.kingstek.companion.models.news.news
import com.kingstek.companion.models.parish.PastoralTeam
import com.kingstek.companion.models.parish.WeekMasses
import com.kingstek.companion.onItemClickListener

class MassAdapter(val sundayMassList: LiveData<ArrayList<String>>?,
                  val weekdayMassList: LiveData<ArrayList<WeekMasses>>?,
                  val mListner: onItemClickListener,
                  val sMass: Boolean,) : RecyclerView.Adapter<MassAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.mass_rec_view, parent, false)
        return ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (sMass) {
            val sundayMass = sundayMassList?.value?.get(position)
            val mass = holder.pMass
            mass.text = sundayMass
            holder.pMassList.visibility = View.GONE

        } else  {
            val sundayMass = weekdayMassList?.value?.get(position)
            val mass = holder.pMass
            mass.text = sundayMass?.day
            val massList = holder.pMassList
            massList.text = sundayMass?.time
        }
    }

    override fun getItemCount(): Int {
        return if (sMass) {
            sundayMassList?.value?.size!!
        }else {
            weekdayMassList?.value?.size!!
        }
    }

    inner class ViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {

        val pMass = itemView.findViewById<TextView>(R.id.tv_mass)
        val pMassList = itemView.findViewById<TextView>(R.id.tv_mass_list)

        init {
            itemView.findViewById<ImageView>(R.id.iv_delete_btn).setOnClickListener {
//                pastoralTeam.value?.removeAt(absoluteAdapterPosition)
                listener.onItemClicked(absoluteAdapterPosition, it)
                notifyDataSetChanged()
            }
        }
    }
}