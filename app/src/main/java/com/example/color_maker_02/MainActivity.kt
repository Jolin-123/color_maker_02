//# Assignment-3 Color-picker Submission
//My submission for CPSC 411, Section 01, Lab Week 8
//* Name: Jiu Lin
//*Email: milklinjob@gmail.com
//*Modified: Oct 15, 2023

package com.example.color_maker_02

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast


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

        buttonOne.setBackgroundResource(R.drawable.button_style)

        // Input filter
        val editTextOne = findViewById<EditText>(R.id.numBox_one)
        val editTextTwo = findViewById<EditText>(R.id.numBox_two)
        val editTextThree = findViewById<EditText>(R.id.numBox_three)

        setDecimalFilter(editTextOne,3)
        setDecimalFilter(editTextTwo,3)
        setDecimalFilter(editTextThree,3)

        numBoxOne.isClickable = false
        numBoxTwo.isClickable = false
        numBoxThree.isClickable = false

        numBoxOne.isEnabled = false
        numBoxTwo.isEnabled = false
        numBoxThree.isEnabled = false

        seekBarOne.isEnabled = false
        seekBarTwo.isEnabled = false
        seekBarThree.isEnabled = false

        // Create an instance of the Rectangle class, initial rectangle color with black
        var rectangle = findViewById<Rectangle>(R.id.customRectangle)

        // Set initial color based on seekBar values
        // updateRectangleColor(rectangle)

        // Reset button set 3 seekBars' value to 0
        buttonOne.setOnClickListener(){
            // Reset the SeekBar value to zero
            seekBarOne.progress = 0
            seekBarTwo.progress = 0
            seekBarThree.progress = 0

            // Turn off the seekbar
            seekBarOne.isEnabled = false
            seekBarTwo.isEnabled = false
            seekBarThree.isEnabled = false

            // Turn off the switch
            switchOne.isChecked = false
            switchTwo.isChecked = false
            switchThree.isChecked = false

            // Turn off the box input
            numBoxOne.isEnabled = false
            numBoxTwo.isEnabled = false
            numBoxThree.isEnabled = false

        }

        // EditText session ******************************************************
        // Set up a TextWatcher for the EditText, controlling EditText to change seek bar value
        numBoxOne.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this example
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the SeekBar value when the EditText value changes
                // Update the SeekBar value when the EditText value changes
                try {
                    if (switchOne.isChecked) {
                        // check input is validate
                        validateInput(s)
                        // Convert string to double
                        val progress = s.toString().toDouble()
                        // Scale to SeekBar range (assuming 0 to 100)
                        seekBarOne.progress = (progress * 100).toInt()
                    }
                } catch (e: NumberFormatException) {
                    // Handle the case where the input is not a valid number
                }
            }
            override fun afterTextChanged(s: Editable?) { }
        })

        numBoxTwo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this example
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the SeekBar value when the EditText value changes
                // Update the SeekBar value when the EditText value changes
                try {
                    if (switchTwo.isChecked) {
                        // check input is validate
                        validateInput(s)
                        // Convert string to double
                        val progress = s.toString().toDouble()
                        // Scale to SeekBar range (assuming 0 to 100)
                        seekBarTwo.progress = (progress * 100).toInt()
                    }
                } catch (e: NumberFormatException) {
                    // Handle the case where the input is not a valid number
                }
            }
            override fun afterTextChanged(s: Editable?) { }
        })

        numBoxThree.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this example
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the SeekBar value when the EditText value changes
                try {
                    if (switchThree.isChecked) {
                        // check input is validate
                        validateInput(s)

                        // Convert string to double
                        val progress = s.toString().toDouble()

                        // Scale to SeekBar range (assuming 0 to 100)
                        seekBarThree.progress = (progress * 100).toInt()
                    }
                } catch (e: NumberFormatException) {
                    // Handle the case where the input is not a valid number
                }
            }
            override fun afterTextChanged(s: Editable?) { }
        })

        // seekBar session  *****************************************
        // seekBar session1 red
        seekBarOne.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                // when seekbar's thumb moves and inside updateRectangleColor() red switcher is "on"
                updateRectangleColor(rectangle)

                // Normalize the progress value to the range 0 to 1, original value is 0-100
                val normalizedProgress = progress.toDouble() / 100
                val redValue = progress

                // Format the normalized progress as a decimal with two decimal places
                numBoxOne.setText(String.format("%.3f", normalizedProgress))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        //seekBar Session2 green
        seekBarTwo.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                updateRectangleColor(rectangle)

                // Normalize the progress value to the range 0 to 1
                val normalizedProgress = progress.toDouble() / 100
                val greenValue = progress

                // Format the normalized progress as a decimal with two decimal places
                numBoxTwo.setText(String.format("%.3f", normalizedProgress))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        //seekBar Session3 blue
        seekBarThree.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                updateRectangleColor(rectangle)

                // Normalize the progress value to the range 0 to 1
                val normalizedProgress = progress.toDouble() / 100
                val blueValue = progress

                // Format the normalized progress as a decimal with two decimal places
                numBoxThree.setText(String.format("%.3f", normalizedProgress))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        // switch session  ***********************************************
        // switch change computer value of red, green, blue to 0 or previous seekbar progress value
        switchOne.setOnCheckedChangeListener { _, isChecked ->
            var red = 0
            var redValue = 0
            // Update the seekBar state based on the switch state
            seekBarOne.isEnabled = isChecked
            // Locked EditText state base on the switch state
            numBoxOne.isEnabled = isChecked
            // Switch is "off", update rectangle color with red = 0
            if(!isChecked){
                //seekBarOne.progress = 0
                updateRectangleColor(rectangle)
                Log.i(TAG,"switch 1 is off")
                //number box is locked
                numBoxOne.isEnabled = false
            }else {
                // switch is "on"
                // red switch turns "on", red valur is current(previous) seekbar.progress value, update rectangle color
                redValue = seekBarOne.progress
                red = (redValue / 100.0 * 255).toInt()
                updateRectangleColor(rectangle)
                Log.i(TAG, "red is $red")
                // *** When EditText box can not be locked, users have bad input ***
                // According to seekbar progress, text box display current seekbar value
                val textNum = redValue / 100.0
                numBoxOne.setText(String.format("%.3f", textNum))
            }
        }

        switchTwo.setOnCheckedChangeListener { _, isChecked ->
            seekBarTwo.isEnabled = isChecked
            numBoxTwo.isEnabled = isChecked
           var  green = 0
            var greenValue = 0
            // switch is "off"
            if(!isChecked){
                //seekBarOne.progress = 0
                updateRectangleColor(rectangle)
                Log.i(TAG,"switch 2 is off")
                // number box is locked
                numBoxTwo.isEnabled = false
            }else {
                // switch is "on"
                // green switch turns "on", green valur is current(previous) seek bar progress value, update rectangle color
                greenValue = seekBarTwo.progress
                green = (greenValue / 100.0 * 255).toInt()
                updateRectangleColor(rectangle)
                Log.i(TAG, "green is $green")
                // *** When EditText box can not be locked, users have bad input ***
                // According to seekbar progress, text box display current seekbar value
                val textNum = greenValue / 100.0
                numBoxTwo.setText(String.format("%.3f", textNum))
            }
        }

        // This section control blue switch
        // Serving for switch button, when switch button is ON, compute the rectangle color with previous blue seek bar
        switchThree.setOnCheckedChangeListener { _, isChecked ->
            seekBarThree.isEnabled = isChecked
            numBoxThree.isEnabled = isChecked
            var blue = 0
            var blueValue = 0

            // switch is "off"
            // blue switch turns "off" compute the rectangle color again, blue = 0
            if(!isChecked){
                //seekBarOne.progress = 0
                updateRectangleColor(rectangle)
                Log.i(TAG,"switch blue is off")
                //number box is locked
                numBoxThree.isEnabled = false
            }else {
                // switch is "on"
                //blue switch turns "on", blue valur is current(previous) seek bar progress value, update rectangle color
                blueValue = seekBarThree.progress
                blue = (blueValue / 100.0 * 255).toInt()
                updateRectangleColor(rectangle)
                Log.i(TAG, "blue is $blue")
                // *** When EditText box can not be locked, users have bad input ***
                // According to seekbar progress, text box display current seekbar value
                val textNum = blueValue / 100.0
                numBoxThree.setText(String.format("%.3f", textNum))
            }
        }
    } // End of onCreate()


    // This function compute the color of rectangle
    // This function is called under seekbar.setOnSeekBarChangeListener()
    private fun updateRectangleColor(rectangle: Rectangle) {
        var red = 0
        var green = 0
        var blue = 0

        // After switch turn "on" and moving red seekbar
        // store the value from current red seek bar progress value
        var redTemp = 0
        redTemp = seekBarOne.progress
        Log.i(TAG,"switch1 in updateRectColor redTemp is $redTemp")
        if (switchOne.isChecked){
            Log.i(TAG,"switch1 in updateRectColor redValue is $redTemp")
            // convert 0-100 from progress to 0-255
            red = (redTemp/100.0 * 255).toInt()
            Log.i(TAG,"switch1 in updateRectColor is on")
        }

        // After switch turn "on" and moving green seekbar
        var greenTemp = 0
        greenTemp = seekBarTwo.progress
        Log.i(TAG,"switch1 in updateRectColor greenTemp is $greenTemp")
        // Switch is "on" and green "seekbar value changes"
        if (switchTwo.isChecked){
            Log.i(TAG,"switch1 in updateRectColor greenValue is $greenTemp")
            green = (greenTemp/100.0 * 255).toInt()
            Log.i(TAG,"switch1 in updateRectColor green is on")
        }

        // After switch turn "on"
        // and get current blue seekbar value to compute rectangle color
        var blueCurr = 0
        blueCurr = seekBarThree.progress
        Log.i(TAG,"switch1 in updateRectColor blueTemp is $blueCurr")

        // Switch is "on" and "seekbar value changes"
        // Serving for seekbar progress; when switch button is ON and moving thumb on seek bar, compute the rectangle color with changed blue seek bar value
       if (switchThree.isChecked){
            Log.i(TAG,"switch1 in updateRectColor blueValue is $blueCurr")
            blue = (blueCurr/100.0 * 255).toInt()
            Log.i(TAG,"switch1 in updateRectColor blue is on")
        }
        return rectangle.setColor(red, green, blue)
    }

    //  check if the input is a valid decimal number
    private fun validateInput(s: CharSequence?) {
        try {
            val input = s?.toString()?.toFloat()
            if (input == null || input > 1.0){
                // Invalid input and do nothing
            }
        } catch (e: NumberFormatException) {
            // Handle the case where the input is not a valid number, do nothing
        }
    }

    // This function restricts the input to a specific number of decimal places
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