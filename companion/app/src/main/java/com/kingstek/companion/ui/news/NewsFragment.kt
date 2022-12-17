package com.kingstek.companion.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingstek.companion.databinding.FragmentNewsBinding
import com.kingstek.companion.ui.home.NewsAdapter
import com.kingstek.companion.onItemClickListener

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)


//        val textView: TextView = binding.textSlideshow
//        newsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        val newsRecyclerView = binding.newsRecyclerViewNewsPage
        val newsAdapter = NewsAdapter(newsViewModel.newsList, object : onItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
//                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show()

                val action = NewsFragmentDirections.actionNavNewsToNewsDetailsFragment(position)
                Navigation.findNavController(view).navigate(action)
            }

        } )

        newsRecyclerView.adapter = newsAdapter
        newsRecyclerView.layoutManager = LinearLayoutManager(context)

        newsRecyclerView.adapter

        //Todo on use of real data make recycler view cardview bigger and use separate adapters
        //Todo override enter for search and remove entering to next line
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}