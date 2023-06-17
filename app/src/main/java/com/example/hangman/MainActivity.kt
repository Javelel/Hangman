package com.example.hangman

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

	private lateinit var btnStart: Button
	private lateinit var modeButton : AppCompatButton

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		btnStart = findViewById(R.id.btnStart)
		modeButton = findViewById(R.id.modeBtn)
		modeButton.setOnClickListener {
			when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
				Configuration.UI_MODE_NIGHT_YES -> {
					// Jeśli jest tryb nocny, przełącz na tryb dzienny
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
					recreate()
				}
				Configuration.UI_MODE_NIGHT_NO -> {
					// Jeśli jest tryb dzienny, przełącz na tryb nocny
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
					recreate()
				}
			}
		}

		btnStart.setOnClickListener {
			val intent = Intent(this, ChooseWordActivity::class.java)
			startActivity(intent)
		}

	}
}