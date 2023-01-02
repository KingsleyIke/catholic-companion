package com.kingstek.companion.ui.calender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentCalenderBinding
import com.kingstek.companion.databinding.FragmentMonthlyViewBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class MonthlyViewFragment : Fragment() {

    private var _binding: FragmentMonthlyViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var calendarMonthlyViewModel: CalendarMonthlyViewModel
    lateinit var widget: MaterialCalendarView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMonthlyViewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        calendarMonthlyViewModel = ViewModelProvider(this)[CalendarMonthlyViewModel::class.java]


        val mydate= CalendarDay.from(2023,  1, 4)
        val mydate2= CalendarDay.from(2023,  1, 5)
        val mydate3= CalendarDay.from(2023,  1, 6)

        val caledarList = listOf<CalendarDay>(mydate)
        val caledarList2 = listOf<CalendarDay>(mydate2, mydate3)

        val caledarTextList = listOf<String>("Feast \n of St. anthony of Padua", "mydate2", "Another text")

        //TODO implement multi lines of text
        val decoratorList = listOf(DecoratorClass(caledarList, "Feast of St. anthony of Padua"),
            DecoratorClass(caledarList2, "St. anthony of Padua"))

        widget = binding.calendarView
        widget.addDecorators(decoratorList)

        return root    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}