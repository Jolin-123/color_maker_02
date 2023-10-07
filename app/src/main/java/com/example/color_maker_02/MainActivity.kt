package com.example.color_maker_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        seekBarOne.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")

                // Normalize the progress value to the range 0 to 1
                val normalizedProgress = progress.toDouble() / 100

                // Format the normalized progress as a decimal with two decimal places
                numBoxOne.setText(String.format("%.2f", normalizedProgress))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
}