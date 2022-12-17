package com.kingstek.companion.ui.parish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentParishDetailsBinding
import com.kingstek.companion.databinding.FragmentParishGalleryBinding
import com.kingstek.companion.onItemClickListener

class ParishGalleryFragment : Fragment() {

    //TODO gliding recylcer view for images with headings or details page for each gallery

    private var _binding: FragmentParishGalleryBinding? = null
    private val binding get() = _binding!!
    val args: ParishGalleryFragmentArgs by navArgs()
    private lateinit var parishGalleryViewModel: ParishGalleryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParishGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val position = args.position

        parishGalleryViewModel = ViewModelProvider(this).get(ParishGalleryViewModel::class.java)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Parish Gallery"

        binding.tvParishName.text =  parishGalleryViewModel.parishList.value?.get(position)?.parishName

        val parishGalleryRecyclerView = binding.rvGalleryRecyclerView

        val parishGalleryAdapter = ParishGalleryAdapter( parishGalleryViewModel.parishList.value?.get(position)?.parishGalerry!!, object : onItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                //TODO("Not yet implemented")
            }
        })

        parishGalleryRecyclerView.adapter = parishGalleryAdapter
        parishGalleryRecyclerView.layoutManager = LinearLayoutManager(context)
        return root
    }

}