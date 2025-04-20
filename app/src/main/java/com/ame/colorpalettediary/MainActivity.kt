package com.ame.colorpalettediary

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
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
        pickColorButton = findViewById(R.id.pickColorButton)
        saveColorButton = findViewById(R.id.saveColorButton)
        colorPreview = findViewById(R.id.colorPreview)

        pickColorButton.setOnClickListener {
            selectedColor = Color.rgb(
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            )
            colorPreview.setBackgroundColor(selectedColor)
        }

        saveColorButton.setOnClickListener {
            Toast.makeText(this, "Color saved (kinda 🧀)", Toast.LENGTH_SHORT).show()
        }
    }
}