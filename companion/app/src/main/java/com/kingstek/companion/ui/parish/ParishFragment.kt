package com.kingstek.companion.ui.parish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kingstek.companion.databinding.FragmentParishBinding

class ParishFragment : Fragment() {

  private lateinit var parishViewModel: ParishViewModel
private var _binding: FragmentParishBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    parishViewModel =
            ViewModelProvider(this).get(ParishViewModel::class.java)

    _binding = FragmentParishBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textGallery
    parishViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}