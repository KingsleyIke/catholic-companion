package com.kingstek.companion.ui.calender

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class DecoratorClass (dates: List<CalendarDay>, eventText:String) : DayViewDecorator {
    private val dates: HashSet<CalendarDay> = HashSet(dates)
    var eventDay = eventText

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(AddTextToDates(eventDay))
        view.setDaysDisabled(true)
    }

}