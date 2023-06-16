package com.example.hangman

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class GameActivity : AppCompatActivity() {

	private lateinit var wordTextView: TextView
	private lateinit var imageView: ImageView
	private lateinit var gameLayout : LinearLayout
	private lateinit var keyboardLayout : TableLayout
	private lateinit var gameLostTextView: TextView
	private lateinit var gameWonTextView: TextView

	private var wordToGuess : String = ""

	private val gameManager = GameManager()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_game)
		wordToGuess = intent.getStringExtra("0").toString()

		wordTextView = findViewById(R.id.wordTV)
		imageView = findViewById(R.id.imageView)
		gameWonTextView = findViewById(R.id.gameWonTV)
		gameLostTextView = findViewById(R.id.gameLostTV)

		gameLayout = findViewById(R.id.gameLayout)  // adding keyboard
		val inflater = LayoutInflater.from(this)
		keyboardLayout = inflater.inflate(R.layout.keyboard, gameLayout, false) as TableLayout
		gameLayout.addView(keyboardLayout)

		val gameState = gameManager.startNewGame(wordToGuess)
		updateUI(gameState)
	}

	fun keyClicked(view: View) {    // When clicked key on the keyboard
		val key : AppCompatButton = view as AppCompatButton
		//val keyChar : String = key.text as String
		//key.isClickable = false
		/*if (wordToGuess.contains(keyChar))
			key.setBackgroundColor(Color.GREEN)
		else
			key.setBackgroundColor(Color.RED)*/
		val gameState = gameManager.play(key)
		updateUI(gameState)
	}

	private fun updateUI(gameState: GameState) {
		when (gameState) {
			is GameState.Lost -> showGameLost(gameState.wordToGuess)
			is GameState.Running -> {
				wordTextView.text = gameState.underscoreWord
				imageView.setImageDrawable(ContextCompat.getDrawable(this, gameState.drawable))
			}
			is GameState.Won -> showGameWon(gameState.wordToGuess)
		}
	}

	private fun showGameLost(wordToGuess: String) {
		wordTextView.text = wordToGuess
		gameLostTextView.visibility = View.VISIBLE
		imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.game7))
		keyboardLayout.visibility = View.GONE
	}

	private fun showGameWon(wordToGuess: String) {
		wordTextView.text = wordToGuess
		gameWonTextView.visibility = View.VISIBLE
		keyboardLayout.visibility = View.GONE
	}

}
