package com.kingstek.companion.ui.parish.updates

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.kingstek.companion.R
import com.kingstek.companion.models.parish.ImageModel
import com.kingstek.companion.onItemClickListener

class ParishImageAdapter(val imageList: LiveData<ArrayList<ImageModel>>, val mListner: onItemClickListener): RecyclerView.Adapter<ParishImageAdapter.ViewHolder> () {

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.parish_images_cardview, parent, false)
        return ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageList = imageList.value?.get(position)

        val title = holder.imageTitle
        title.text = imageList?.title

        val image = holder.image
        val uri: Uri? = imageList?.imageUri
        val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, uri)
        image.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return imageList.value?.size!!
    }


    inner class ViewHolder(intemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(intemView) {
        val imageTitle = itemView.findViewById<TextView>(R.id.tv_image_title)
        val image = itemView.findViewById<ImageView>(R.id.iv_selected_image)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.iv_delete_btn)

        init {
            itemView.findViewById<ImageView>(R.id.iv_delete_btn).setOnClickListener {
                listener.onItemClicked(absoluteAdapterPosition, it)
                notifyDataSetChanged()
            }
        }

    }
}