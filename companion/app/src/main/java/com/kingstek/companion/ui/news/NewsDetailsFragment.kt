package com.kingstek.companion.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kingstek.companion.databinding.FragmentNewsDetailsBinding


class NewsDetailsFragment : Fragment() {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!
    val args: NewsDetailsFragmentArgs by navArgs()
    private lateinit var newsDetailsViewModel: NewsDetailsViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val position = args.position


        newsDetailsViewModel = ViewModelProvider(this).get(NewsDetailsViewModel::class.java)

        //set Fragment title using news Details
        (requireActivity() as AppCompatActivity).supportActionBar?.title = newsDetailsViewModel.newsList.value?.get(position)?.headline


        binding.newsDetails.text = newsDetailsViewModel.newsList.value?.get(position)?.detail
        binding.newsHeading.text = newsDetailsViewModel.newsList.value?.get(position)?.headline
        binding.titleImage.setImageResource(newsDetailsViewModel.newsList.value?.get(position)?.headlineImage!!)


        //Todo Optimize scrolling to show heading when it is past
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}