package com.example.color_maker_02

import android.content.Context
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import org.w3c.dom.Attr
class Rectangle @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attr, defStyleAttr) {

    val paint: Paint = Paint()
    val rectStroke: Paint = Paint()


    // Initialize with a default color
    init {
        //fill
        paint.color = Color.BLACK
        // stroke for rectangle
        rectStroke.setStyle(Paint.Style.STROKE);
        rectStroke.setColor(Color.BLACK);
        rectStroke.setStrokeWidth(15F);
    }


    // Set the color based on RGB values
    fun setColor(red: Int, green: Int, blue: Int) {
        paint.setARGB(255, red, green, blue)
        // Request a redraw
        invalidate()
    }


    // Drawing the rectangle using draw.RoundRect() function
    private val radius: Float = resources.displayMetrics.density * 30f
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat()*0.8f, radius, radius,paint)  // paint
        canvas?.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat()*0.8f, radius, radius,rectStroke) // stroke
    }
}


