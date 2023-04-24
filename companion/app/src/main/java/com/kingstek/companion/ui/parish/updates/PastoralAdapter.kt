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
import com.kingstek.companion.onItemClickListener

class PastoralAdapter(val pastoralTeam: LiveData<ArrayList<PastoralTeam>>, val mListner: onItemClickListener) : RecyclerView.Adapter<PastoralAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.pastoral_team, parent, false)
        return ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pastoralTeam = pastoralTeam.value?.get(position)

        val name = holder.pName
        name.text = pastoralTeam?.name

        val position = holder.pPosition
        position.text = pastoralTeam?.postion

        val title = holder.pTitle
        title.text = pastoralTeam?.title

        val deleteBtn = holder.deleteBtn


    }

    override fun getItemCount(): Int {
        return pastoralTeam.value?.size!!
    }

    inner class ViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {

        val pName = itemView.findViewById<TextView>(R.id.tv_pastoral_name)
        val pPosition = itemView.findViewById<TextView>(R.id.tv_pastoral_position)
        val pTitle = itemView.findViewById<TextView>(R.id.tv_pastoral_title)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.iv_delete_btn)

        init {
            itemView.findViewById<ImageView>(R.id.iv_delete_btn).setOnClickListener {
//                pastoralTeam.value?.removeAt(absoluteAdapterPosition)
                listener.onItemClicked(absoluteAdapterPosition, it)
                notifyDataSetChanged()
            }
        }
    }
}