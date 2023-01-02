package com.kingstek.companion.ui.calender

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan

class AddTextToDates (text: String ): LineBackgroundSpan {

    private var dayEvent = text

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lnum: Int
    ) {
        canvas.drawText(dayEvent,((left+right)/4).toFloat(),(bottom+15).toFloat(),paint)
    }
}