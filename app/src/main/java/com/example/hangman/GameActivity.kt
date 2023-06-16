package com.example.hangman

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

	private lateinit var wordTextView: TextView
	private lateinit var imageView: ImageView

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_game)
		wordTextView = findViewById(R.id.wordTV)
		imageView = findViewById(R.id.imageView)

	}
}
