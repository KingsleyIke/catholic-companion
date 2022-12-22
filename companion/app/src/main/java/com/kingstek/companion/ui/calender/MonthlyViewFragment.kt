package com.kingstek.companion.ui.calender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentCalenderBinding
import com.kingstek.companion.databinding.FragmentMonthlyViewBinding

class MonthlyViewFragment : Fragment() {

    private var _binding: FragmentMonthlyViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMonthlyViewBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}