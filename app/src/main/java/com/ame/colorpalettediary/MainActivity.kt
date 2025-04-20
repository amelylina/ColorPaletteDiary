package com.ame.colorpalettediary

import android.graphics.Color
import android.os.Bundle
import android.renderscript.RenderScript
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var pickColorButton:Button
    private lateinit var  saveColorButton:Button
    private lateinit var colorPreview:View

    private var selectedColor:Int = Color.GRAY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val blurView = findViewById<eightbitlab.com.blurview.BlurView>(R.id.blurView)

        val rootView = window.decorView.findViewById<ViewGroup>(android.R.id.content)

        val blurRadius = 16f

        blurView.setupWith(rootView)
            .setFrameClearDrawable(window.decorView.background)
            .setBlurRadius(blurRadius)

        pickColorButton = findViewById(R.id.pickColorButton)
        saveColorButton = findViewById(R.id.saveColorButton)
        colorPreview = findViewById(R.id.colorPreview)

        val redSeekerBar = findViewById<SeekBar>(R.id.redSeekBar)
        val greenSeekerBar = findViewById<SeekBar>(R.id.greenSeekBar)
        val blueSeekerBar = findViewById<SeekBar>(R.id.blueSeekBar)

        val redLabel = findViewById<TextView>(R.id.redLabel)
        val greenLabel = findViewById<TextView>(R.id.greenLabel)
        val blueLabel = findViewById<TextView>(R.id.blueLabel)

        val mainContent = findViewById<LinearLayout>(R.id.mainContent)
        val colorPreviewAlt = findViewById<View>(R.id.colorPreviewAlt)
        val doneButton = findViewById<Button>(R.id.doneButton)

        fun updateColorPreview() {
            val r = redSeekerBar.progress
            val g = greenSeekerBar.progress
            val b = blueSeekerBar.progress
            selectedColor = Color.rgb(r,g,b)
            colorPreview.setBackgroundColor(selectedColor)
            colorPreviewAlt.setBackgroundColor(selectedColor)
            redLabel.text = "Red: $r"
            greenLabel.text = "Green: $g"
            blueLabel.text = "Blue: $b"
        }

        val listener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                updateColorPreview()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        }

        val sliderGroup = findViewById<LinearLayout>(R.id.sliderGroup)

        redSeekerBar.setOnSeekBarChangeListener(listener)
        greenSeekerBar.setOnSeekBarChangeListener(listener)
        blueSeekerBar.setOnSeekBarChangeListener(listener)

        pickColorButton.setOnClickListener {
            blurView.visibility = View.VISIBLE
            sliderGroup.visibility = View.VISIBLE
            updateColorPreview()
        }

        doneButton.setOnClickListener {
            blurView.visibility = View.GONE
            sliderGroup.visibility = View.GONE
        }

        saveColorButton.setOnClickListener {
            Toast.makeText(this, "Color saved (kinda 🧀)", Toast.LENGTH_SHORT).show()
        }
    }
}