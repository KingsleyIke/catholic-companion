package com.kingstek.companion.ui.parish

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentParishDetailsBinding
import com.kingstek.companion.ui.news.NewsDetailsFragmentArgs

class ParishDetailsFragment : Fragment() {

    private var _binding: FragmentParishDetailsBinding? = null
    private val binding get() = _binding!!
    val args: ParishDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: ParishDetailsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParishDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val position = args.position

        viewModel = ViewModelProvider(this).get(ParishDetailsViewModel::class.java)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = viewModel.parishList.value?.get(position)?.parishName

        val parishList = viewModel.parishList.value?.get(position)

        val sundayMassList = parishList?.sundayMass
        val weekdayMassList = parishList?.weekDayMass
        val pastoralTeamList = parishList?.pastoralTeam
        val announcementList = parishList?.churchAnnouncements
        val activitiesList = parishList?.churchActivities

//        text.text = sundayMassList!![1]
//        binding.ltSundayMass.addView(view)

        binding.tvParishName.text = parishList?.parishName
        binding.tvParishAddress.text = parishList?.address + ", " + parishList?.deanery + ", " + parishList?.diocese

        //set sunday masses with values fro parish  list
        if (sundayMassList != null) {
            for (i in sundayMassList.indices) {
                val view: View = View.inflate(context, R.layout.mass_cardview, null)
                val massText: TextView =  view.findViewById<TextView>(R.id.tv_mass_text)
                massText.text = sundayMassList[i]
                binding.ltSundayMass.addView(view)
            }
        }

        //set weeekday masses with values fro parish  list
        if (weekdayMassList != null) {
            for (i in weekdayMassList.indices) {
                val view: View = View.inflate(context, R.layout.mass_cardview, null)
                val massText: TextView =  view.findViewById<TextView>(R.id.tv_mass_text)
                massText.text = weekdayMassList[i].day + " " + weekdayMassList[i].time
                binding.ltWeekdayMass.addView(view)
            }
        }

        //set pastoral team with values fro parish  list
        if (pastoralTeamList != null) {
            for (i in pastoralTeamList.indices) {
                val view: View = View.inflate(context, R.layout.pastoral_cardview, null)
                val positionText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_position)
                val nameText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_name)
                val numberText: TextView =  view.findViewById<TextView>(R.id.tv_team_member_number)
                positionText.text = pastoralTeamList[i].postion
                nameText.text = pastoralTeamList[i].name
                numberText.text = pastoralTeamList[i].phoneNumber
                binding.ltPastoralTeam.addView(view)
            }
        }

        //set activities with values from parish  list
        if (activitiesList != null) {
            for (i in activitiesList.indices) {
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
        }

        //set annoucement with values from parish  list
        if (announcementList != null) {
            for (i in announcementList.indices) {
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
        }

        //Todo perform all loops asynchronously to reduce time
        //todo Display no data when List is empty for all text

        /**
         * check if user is signed in
         * and set values accordingly
         */

        //TODO make use of dataBinding to set values
        //TODO add dynamic visibility change
        viewModel.isUserSignedIn()
        if (viewModel.loggegIn.value!!) {
            binding.tvUpdateParishInfo.visibility = View.VISIBLE
            binding.ctContributor.visibility = View.INVISIBLE
        } else {
            binding.tvUpdateParishInfo.visibility = View.INVISIBLE
            binding.ctContributor.visibility = View.VISIBLE
        }

        binding.ctParishGallery.setOnClickListener{
            val action = ParishDetailsFragmentDirections.actionParishDetailsFragmentToParishGalleryFragment(position)
            Navigation.findNavController(it).navigate(action)
        }

        binding.btnBecomeContributor.setOnClickListener{
            it.findNavController().navigate(R.id.logInFragment)
        }

        binding.tvUpdateParishInfo.setOnClickListener {

            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("What do You want to update")
            alertDialog.setItems(arrayOf<CharSequence>(
                "Update Pastoral team and Images",
                "Update Parish Info and Activities"
            ),
            DialogInterface.OnClickListener { dialog , which->
                when (which) {
                    0 -> {
                        findNavController().navigate(R.id.updateParishInfoFragment)
                    }
                    1 -> {
                        findNavController().navigate(R.id.updateMassFragment)
                    }
                }
            })
            alertDialog.setCancelable(false)
            alertDialog.show()

//            it.findNavController().navigate(R.id.updateParishInfoFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}