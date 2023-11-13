package com.example.services

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextTime)
        val buttonStart = findViewById<Button>(R.id.buttonStart)

        buttonStart.setOnClickListener {
            val time = editText.text.toString().toIntOrNull() ?: 0
            Intent(this, CountdownService::class.java).also { intent ->
                intent.putExtra(CountdownService.COUNTDOWN_TIME, time)
                startService(intent)
            }
        }
    }
}
