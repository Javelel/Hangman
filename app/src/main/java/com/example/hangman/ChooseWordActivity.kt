package com.example.hangman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.get
import kotlin.random.Random

private const val WORD_TO_SHORT : String = "Word must have at least 2 characters"

class ChooseWordActivity : AppCompatActivity() {
	private lateinit var chooseWordLayout : LinearLayout
	private lateinit var keyboardLayout : TableLayout
	private lateinit var chooseWordTV : TextView
	private lateinit var okBtn : AppCompatButton
	private lateinit var backspaceBtn : AppCompatImageButton
	private lateinit var randomWordButton: AppCompatButton

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_choose_word)

		chooseWordTV = findViewById(R.id.chooseWordTV)
		chooseWordTV.text = ""

		okBtn = findViewById(R.id.okBtn)
		okBtn.setOnClickListener {
			if (chooseWordTV.text.length < 2) {
				Toast.makeText(this, WORD_TO_SHORT, Toast.LENGTH_LONG).show()
			} else {
				val intent = Intent(this, GameActivity::class.java)
				intent.putExtra("0", chooseWordTV.text)
				startActivity(intent)
			}
		}

		randomWordButton = findViewById(R.id.randomWordBtn) // choosing random word
		randomWordButton.setOnClickListener {
			val fileReader = TextFileReader(this)
			val wordsArray = fileReader.readTextFile(R.raw.words_en)
			val randomIndex = Random.nextInt(0, wordsArray.size)
			val intent = Intent(this, GameActivity::class.java)
			intent.putExtra("0", wordsArray[randomIndex])
			startActivity(intent)
		}

		chooseWordLayout = findViewById(R.id.chooseWordLayout)  // adding keyboard
		val inflater = LayoutInflater.from(this)
		keyboardLayout = inflater.inflate(R.layout.keyboard, chooseWordLayout, false) as TableLayout
		chooseWordLayout.addView(keyboardLayout)

		backspaceBtn = keyboardLayout.findViewById(R.id.backspaceBtn)
		backspaceBtn.isClickable = true
		backspaceBtn.visibility = View.VISIBLE
		backspaceBtn.setOnClickListener { deleteLastChar() }
	}

	private fun deleteLastChar() {
		var currentText : String = chooseWordTV.text as String
		currentText = currentText.dropLast(1)
		chooseWordTV.text = currentText
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