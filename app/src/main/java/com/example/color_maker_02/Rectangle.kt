package com.example.color_maker_02

import android.content.Context
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import org.w3c.dom.Attr

class Rectangle @JvmOverloads constructor(context: Context, attr: AttributeSet?=null, defStyleAttr: Int = 0
): View(context, attr, defStyleAttr){

    val paint: Paint = Paint()
    //paint.setColor(Color.BLUE)

    init {
        //val paint: Paint = Paint()
        paint.color = Color.BLUE
    }

    // Also, you might want to override onDraw method and use 'paint' to draw something on the canvas
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Example: Draw a rectangle on the canvas using the paint color
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }
}