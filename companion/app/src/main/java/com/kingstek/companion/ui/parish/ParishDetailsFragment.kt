package com.kingstek.companion.ui.parish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentParishDetailsBinding
import com.kingstek.companion.ui.news.NewsDetailsFragmentArgs

class ParishDetailsFragment : Fragment() {

    private var _binding: FragmentParishDetailsBinding? = null
    private val binding get() = _binding!!
    val args: ParishDetailsFragmentArgs by navArgs()
    private lateinit var parishDetailsViewModel: ParishDetailsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParishDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val position = args.position

        parishDetailsViewModel = ViewModelProvider(this).get(ParishDetailsViewModel::class.java)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = parishDetailsViewModel.parishList.value?.get(position)?.parishName


        val sundayMassList = parishDetailsViewModel.parishList.value?.get(position)?.sundayMas
        val weekdayMassList = parishDetailsViewModel.parishList.value?.get(position)?.weekDayMass
        val pastoralTeamList = parishDetailsViewModel.parishList.value?.get(position)?.pastoralTeam
        val announcementList = parishDetailsViewModel.parishList.value?.get(position)?.churchAnnouncements
        val activitiesList = parishDetailsViewModel.parishList.value?.get(position)?.churchActivities


//        text.text = sundayMassList!![1]
//        binding.ltSundayMass.addView(view)

        //set sunday masses with values fro parish  list
        for (i in sundayMassList!!.indices) {
            val view: View = View.inflate(context, R.layout.mass_cardview, null)
            val massText: TextView =  view.findViewById<TextView>(R.id.tv_mass_text)
            massText.text = sundayMassList[i]
            binding.ltSundayMass.addView(view)
        }

        //set weeekday masses with values fro parish  list
        for (i in weekdayMassList!!.indices) {
            val view: View = View.inflate(context, R.layout.mass_cardview, null)
            val massText: TextView =  view.findViewById<TextView>(R.id.tv_mass_text)
            massText.text = weekdayMassList[i].day + " " + weekdayMassList[i].time
            binding.ltWeekdayMass.addView(view)
        }

        //todo fix random crashing issue on load of weekday mass view

        //set pastoral team with values fro parish  list
        for (i in pastoralTeamList!!.indices) {
            val view: View = View.inflate(context, R.layout.pastoral_cardview, null)
            val positionText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_position)
            val nameText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_name)
            val numberText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_number)
            positionText.text = pastoralTeamList[i].postion
            nameText.text = pastoralTeamList[i].name
            numberText.text = pastoralTeamList[i].phoneNumber
            binding.ltPastoralTeam.addView(view)
        }

        //set activities with values from parish  list
        for (i in activitiesList!!.indices) {
            val view: View = View.inflate(context, R.layout.pastoral_cardview, null)
            val positionText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_position)
            val nameText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_name)
            val numberText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_number)
            val lineSeperator: View = view.findViewById(R.id.line_seperator)
            lineSeperator.visibility = View.GONE
            numberText.visibility = View.GONE
//            val numberText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_number)
            positionText.text = activitiesList[i].title
            nameText.text = activitiesList[i].dayAndTime
//            numberText.text = pastoralTeamList[i].phoneNumber
            binding.ctActivities.addView(view)
//            binding.ltSundayMass.removeView(view)
        }

        //set annoucement with values from parish  list
        for (i in announcementList!!.indices) {
            val view: View = View.inflate(context, R.layout.pastoral_cardview, null)
            val positionText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_position)
            val nameText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_name)
            val numberText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_number)
            val lineSeperator: View = view.findViewById(R.id.line_seperator)
            lineSeperator.visibility = View.GONE
            numberText.visibility = View.GONE
//            val numberText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_number)
            positionText.text = announcementList[i].title
            nameText.text = announcementList[i].details
//            numberText.text = pastoralTeamList[i].phoneNumber
            binding.ctAnnouncement.addView(view)
//            binding.ltSundayMass.removeView(view)
        }

        //Todo perform all loops asynchronously to reduce time

        //todo Display no data when List is empty for all text
        return root
    }

}