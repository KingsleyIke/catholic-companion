package com.kingstek.companion.ui.calender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingstek.companion.databinding.FragmentFeastDetailBinding

class FeastDetailFragment : Fragment() {

    private var _binding: FragmentFeastDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeastDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}