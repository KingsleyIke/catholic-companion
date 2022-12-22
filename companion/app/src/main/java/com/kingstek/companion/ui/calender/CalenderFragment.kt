package com.kingstek.companion.ui.calender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentCalenderBinding
import com.kingstek.companion.databinding.FragmentParishDetailsBinding
import com.kingstek.companion.ui.parish.ParishDetailsFragmentArgs
import com.kingstek.companion.ui.parish.ParishDetailsViewModel

class CalenderFragment : Fragment() {

    private var _binding: FragmentCalenderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCalenderBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}