package com.example.hangman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

private lateinit var btnStart: Button

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		btnStart = findViewById(R.id.btnStart)

		btnStart.setOnClickListener {
			val intent = Intent(this, GameActivity::class.java)
			startActivity(intent)
		}

	}
}