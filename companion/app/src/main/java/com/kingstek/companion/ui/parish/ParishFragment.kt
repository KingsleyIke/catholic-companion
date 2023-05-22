package com.kingstek.companion.ui.parish

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingstek.companion.databinding.FragmentParishBinding
import com.kingstek.companion.models.parish.Parish
import com.kingstek.companion.onItemClickListener
import kotlinx.coroutines.launch

class ParishFragment : Fragment() {

  private lateinit var parishViewModel: ParishViewModel
private var _binding: FragmentParishBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentParishBinding.inflate(inflater, container, false)
    val root: View = binding.root

    parishViewModel = ViewModelProvider(this).get(ParishViewModel::class.java)

//    val textView: TextView = binding.textGallery
//    parishViewModel.text.observe(viewLifecycleOwner, Observer {
//      textView.text = it
//    })

        //set Up recyclerview and adapter
      //todo what happens when this fails or no network
      lifecycleScope.launch{
          try {
              val parishList: List<Parish> = parishViewModel.getParishList()

              val parishRecyclerView = binding.parishRecyclerView

              val parishAdapter = ParishAdapter(parishList, object : onItemClickListener {
                  override fun onItemClicked(position: Int, view: View) {
                      parishViewModel.sortVisiblity.observe(viewLifecycleOwner, Observer {
                          if (it) {
//                      Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show()
                              val action = ParishFragmentDirections.actionNavParishToParishDetailsFragment(position)
                              Navigation.findNavController(view).navigate(action)
                          } else{
                              Log.d("Do noth", "Do nothing")
                          }
                      })
                  }
              }, requireContext())

              parishRecyclerView.adapter = parishAdapter
              parishRecyclerView.layoutManager = LinearLayoutManager(context)

          } catch (e: Exception) {
              e.printStackTrace()
          }
      }




      //Display filter screen on click of filter button
      binding.btnFilter.setOnClickListener{
          binding.ltFilterContainer.visibility = View.VISIBLE
          parishViewModel.setSortVisiblity(false)
      }

      binding.btnFilterSelection.setOnClickListener {
          binding.ltFilterContainer.visibility = View.INVISIBLE
          parishViewModel.setSortVisiblity(true)

          //todo set parish list based on selction from filter
      }

      binding.btnFilterCancel.setOnClickListener {
          binding.ltFilterContainer.visibility = View.INVISIBLE
          parishViewModel.setSortVisiblity(true)
      }

    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}