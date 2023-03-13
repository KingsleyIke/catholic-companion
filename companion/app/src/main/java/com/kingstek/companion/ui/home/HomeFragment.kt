package com.kingstek.companion.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentHomeBinding
import com.kingstek.companion.onItemClickListener


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.isUserSignedIn()

//    val textView: TextView =
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHomeNews.text = it
        })

        binding.textHomeNews.setOnClickListener {
            it.findNavController().navigate(com.kingstek.companion.R.id.nav_news)

//      Navigation.findNavController(it).navigate(R.id.nav_news)
//      Navigation.findNavController(it).navigate(R.id.action_nav_home_to_nav_news)

        }

        val newsRecyclerView = binding.newsRecyclerView
        val homeRecyclerView = binding.homeRecyclerView

        val newsAdapter = NewsAdapter(homeViewModel.newsList, object : onItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val action = HomeFragmentDirections.actionNavHomeToNewsDetailsFragment(position)
                Navigation.findNavController(view).navigate(action)

            }

        })
        val homeAdapter = HomeAdapter(homeViewModel.homeList, object : onItemClickListener {
          override fun onItemClicked(position: Int, view: View) {

              when (position) {
                  0 -> {
                      view.findNavController().navigate(R.id.mapsFragment)
                  }
                  1 -> {
                      view.findNavController().navigate(R.id.nav_calender)
                  }
                  2 -> {
                      view.findNavController().navigate(R.id.nav_parish)
                  }
                  3 -> {
                      view.findNavController().navigate(R.id.nav_readings)
                  }
                  4 -> {
                      view.findNavController().navigate(R.id.nav_catechism)
                  }
                  5 -> {
                      view.findNavController().navigate(R.id.nav_prayers)
                  }
              }
          }

        })

        newsRecyclerView.adapter = newsAdapter
        newsRecyclerView.layoutManager = LinearLayoutManager(context)

        homeRecyclerView.adapter = homeAdapter
        homeRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}