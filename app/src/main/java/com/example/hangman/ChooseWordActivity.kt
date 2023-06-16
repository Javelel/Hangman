package com.example.hangman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

private lateinit var chooseWordLayout : LinearLayout
private lateinit var keyboardLayout : TableLayout
private lateinit var chooseWordTV : TextView
private lateinit var okBtn : AppCompatButton

class ChooseWordActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_choose_word)

		chooseWordTV = findViewById(R.id.chooseWordTV)
		chooseWordTV.text = ""

		okBtn = findViewById(R.id.okBtn)
		okBtn.setOnClickListener {
			val intent = Intent(this, GameActivity::class.java)
			intent.putExtra("0", chooseWordTV.text)
			startActivity(intent)
		}

		chooseWordLayout = findViewById(R.id.chooseWordLayout)  // adding keyboard
		val inflater = LayoutInflater.from(this)
		keyboardLayout = inflater.inflate(R.layout.keyboard, chooseWordLayout, false) as TableLayout
		chooseWordLayout.addView(keyboardLayout)

	}



	fun keyClicked(view : View) {    // When clicked key on the keyboard
		val key : AppCompatButton = view as AppCompatButton
		val keyChar : String = key.text as String
		addCharToWord(keyChar)
	}

	private fun addCharToWord(keyChar : String) {
		if (chooseWordTV.text.length >= 18)
			return

		val newText : String = (chooseWordTV.text as String) + keyChar
		chooseWordTV.text = newText
	}

}