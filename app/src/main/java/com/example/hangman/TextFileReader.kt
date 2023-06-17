package com.example.hangman

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class TextFileReader(private val context: Context) {
	fun readTextFile(resourceId: Int): Array<String> {
		val inputStream: InputStream = context.resources.openRawResource(resourceId)
		val wordsList = mutableListOf<String>()
		try {
			val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
			var line: String?
			while (reader.readLine().also { line = it } != null) {
				wordsList.add(line!!)
			}
			reader.close()
			inputStream.close()
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return wordsList.toTypedArray()
	}
}
