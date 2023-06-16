package com.example.hangman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

	private lateinit var wordTextView: TextView
	private lateinit var imageView: ImageView
	private lateinit var gameLayout : LinearLayout
	private lateinit var keyboardLayout : TableLayout

	private var wantedWord : String = ""

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_game)
		wantedWord = intent.getStringExtra("0").toString()

		wordTextView = findViewById(R.id.wordTV)
		imageView = findViewById(R.id.imageView)

		gameLayout = findViewById(R.id.gameLayout)  // adding keyboard
		val inflater = LayoutInflater.from(this)
		keyboardLayout = inflater.inflate(R.layout.keyboard, gameLayout, false) as TableLayout
		gameLayout.addView(keyboardLayout)
	}

	fun keyClicked(view: View) {    // When clicked key on the keyboard
		println(wantedWord)
	}
}
