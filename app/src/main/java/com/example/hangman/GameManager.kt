package com.example.hangman

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton


class GameManager {

	private val maxTries = 7
	private var currentTries = 0
	private lateinit var underscoreWord: String
	private var drawable: Int = R.drawable.game0
	private lateinit var wordToGuess: String

	fun startNewGame(wordToGuess: String): GameState {
		this.wordToGuess = wordToGuess
		currentTries = 0
		drawable = R.drawable.game7
		generateUnderscores(this.wordToGuess)
		return getGameState()
	}

	fun play(key: AppCompatButton): GameState {
		key.isClickable = false
		val keyChar : Char = (key.text as String).single()

		val indexes = mutableListOf<Int>()
		wordToGuess.forEachIndexed { index, char ->
			if (char.equals(keyChar, true))
				indexes.add(index)
		}

		var finalUnderscoreWord = "" + underscoreWord // _ _ _ _ _ _ _ -> E _ _ _ _ _ _
		indexes.forEach { index ->
			val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, keyChar) }
			finalUnderscoreWord = sb.toString()
		}
		if (indexes.isEmpty()) {
			currentTries++
			key.setBackgroundColor(Color.RED)
		} else {
			key.setBackgroundColor(Color.GREEN)
		}

		underscoreWord = finalUnderscoreWord
		return getGameState()
	}

	fun generateUnderscores(word: String) {
		val sb = StringBuilder()
		word.forEach { char ->
			sb.append("_")
		}
		underscoreWord = sb.toString()
	}

	private fun getHangmanDrawable(): Int {
		return when (currentTries) {
			0 -> R.drawable.game0
			1 -> R.drawable.game1
			2 -> R.drawable.game2
			3 -> R.drawable.game3
			4 -> R.drawable.game4
			5 -> R.drawable.game5
			6 -> R.drawable.game6
			7 -> R.drawable.game7
			else -> R.drawable.game7
		}
	}

	private fun getGameState(): GameState {
		if (underscoreWord.equals(wordToGuess, true)) {
			return GameState.Won(wordToGuess)
		}

		if (currentTries == maxTries) {
			return GameState.Lost(wordToGuess)
		}

		drawable = getHangmanDrawable()
		return GameState.Running(underscoreWord, drawable)
	}


}