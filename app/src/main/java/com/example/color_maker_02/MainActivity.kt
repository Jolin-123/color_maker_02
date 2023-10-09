package com.example.color_maker_02

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch


const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var buttonOne: Button

    private lateinit var seekBarOne: SeekBar
    private lateinit var seekBarTwo: SeekBar
    private lateinit var seekBarThree: SeekBar

    private lateinit var switchOne: Switch
    private lateinit var switchTwo: Switch
    private lateinit var switchThree: Switch

    private lateinit var numBoxOne: EditText
    private lateinit var numBoxTwo: EditText
    private lateinit var numBoxThree: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchOne = findViewById(R.id.switch_one)
        switchTwo = findViewById(R.id.switch_two)
        switchThree = findViewById(R.id.switch_three)

        seekBarOne = findViewById(R.id.seekBar)
        seekBarTwo = findViewById(R.id.seekBar_two)
        seekBarThree = findViewById(R.id.seekBar_three)

        numBoxOne = findViewById(R.id.numBox_one)
        numBoxTwo = findViewById(R.id.numBox_two)
        numBoxThree = findViewById(R.id.numBox_three)

        buttonOne = findViewById(R.id.button_one)

        // Input filter
        val editTextOne = findViewById<EditText>(R.id.numBox_one)
        val editTextTwo = findViewById<EditText>(R.id.numBox_two)
        val editTextThree = findViewById<EditText>(R.id.numBox_three)

        setDecimalFilter(editTextOne,3)
        setDecimalFilter(editTextTwo,3)
        setDecimalFilter(editTextThree,3)


//        val decimalFilter = InputFilter { source, start, end, dest, dstart, dend ->
//            val newInput = dest.toString().substring(0, dstart) +
//                    source.toString().substring(start, end) +
//                    dest.toString().substring(dend)
//
//            // Use a regex pattern to allow only numbers with up to 2 decimal places
//            val pattern = "^\\d*\\.?\\d{0,2}\$".toRegex()
//
//            return@InputFilter if (newInput.isEmpty() || pattern.matches(newInput)) {
//                null // Accept the input
//            } else {
//                ""  // Reject the input
//            }
//        }
//        editText.filters = arrayOf(decimalFilter)

        // Create an instance of the Rectangle class
        var rectangle = findViewById<Rectangle>(R.id.customRectangle)

        // Set initial color based on seekBar values
        updateRectangleColor(rectangle)

        // Control turn on and off for 3 switches
        switchOnOff()

        // Reset button set 3 seekBar values to 0
        buttonOne.setOnClickListener(){

            // Reset the SeekBar value to zero
            seekBarOne.progress = 0
            seekBarTwo.progress = 0
            seekBarThree.progress = 0

            // Turn off the switch
            switchOne.isChecked = false
            switchTwo.isChecked = false
            switchThree.isChecked = false
        }

        //seekBar session 1
        seekBarOne.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")

                updateRectangleColor(rectangle)


                // Normalize the progress value to the range 0 to 1
                val normalizedProgress = progress.toDouble() / 100
                val redValue = progress

                // Format the normalized progress as a decimal with two decimal places
                numBoxOne.setText(String.format("%.1f", normalizedProgress))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        //seekBar Session 2
        seekBarTwo.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")

                updateRectangleColor(rectangle)

                // Normalize the progress value to the range 0 to 1
                val normalizedProgress = progress.toDouble() / 100
                val greenValue = progress

                // Format the normalized progress as a decimal with two decimal places
                numBoxTwo.setText(String.format("%.2f", normalizedProgress))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        //seekBar Session 3
        seekBarThree.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")

                updateRectangleColor(rectangle)

                // Normalize the progress value to the range 0 to 1
                val normalizedProgress = progress.toDouble() / 100
                val blueValue = progress

                // Format the normalized progress as a decimal with two decimal places
                numBoxThree.setText(String.format("%.2f", normalizedProgress))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })



//        // Set ARGB values for the paint object in the Rectangle
//        rectangle.paint.setARGB(255, 100, 50, 150)
//
//        // Optionally, you can call invalidate to force a redraw
//        rectangle.invalidate()
//
//        // Now you can use this rectangle in your layout or draw it on a canvas
//        setContentView(rectangle)

    } // End of onCreate function


    // Method to update the color of the rectangle based on SeekBar values
    private fun updateRectangleColor(rectangle: Rectangle) {
        val redValue = seekBarOne.progress
        val greenValue = seekBarTwo.progress
        val blueValue = seekBarThree.progress

        val red: Int = (redValue/100.0 * 255).toInt()
        val green: Int = (greenValue/100.0 * 255).toInt()
        val blue: Int = (blueValue/100.0 * 255).toInt()

        return rectangle.setColor(red, green, blue)

     //rectangle.paint.setARGB(255, redValue, greenValue, blueValue)
    }
    private fun switchOnOff() {
        // Set the initial state based on the switch state
        seekBarOne.isEnabled = switchOne.isChecked
        switchOne.setOnCheckedChangeListener { _, isChecked ->
            // Update the seekBar state based on the switch state
            seekBarOne.isEnabled = isChecked
            val trackColorStateList = ColorStateList.valueOf(Color.RED)
        }
        seekBarTwo.isEnabled = switchTwo.isChecked
        switchTwo.setOnCheckedChangeListener { _, isChecked ->
            seekBarTwo.isEnabled = isChecked
        }

        seekBarThree.isEnabled = switchThree.isChecked
        switchThree.setOnCheckedChangeListener { _, isChecked ->
            seekBarThree.isEnabled = isChecked
        }

//        numBoxThree.isEnabled = switchThree.isChecked
//        numBoxThree.setOnCheckedChangeListener  { _, isChecked ->
//            numBoxThree.isEnabled = isChecked
//        }
    }  // switch function off


    private fun setDecimalFilter(editText: EditText, decimalPlaces: Int) {
        val decimalFilter = InputFilter { source, start, end, dest, dstart, dend ->
            val newInput = dest.toString().substring(0, dstart) +
                    source.toString().substring(start, end) +
                    dest.toString().substring(dend)

            // Use a regex pattern to allow only numbers with the specified decimal places
            val pattern = "^\\d*\\.?\\d{0,$decimalPlaces}\$".toRegex()

            return@InputFilter if (newInput.isEmpty() || pattern.matches(newInput)) {
                null // Accept the input
            } else {
                ""  // Reject the input
            }
        }
        editText.filters = arrayOf(decimalFilter)
    }

}


