package com.boring.gangmin.counter.data

import android.content.Context
import com.boring.gangmin.counter.data.utils.transferStringToInt
import kotlinx.coroutines.flow.MutableStateFlow

class NumberRepository {
    private val number = MutableStateFlow(0)
    val text = MutableStateFlow("0")

    fun add() {
        number.value += 1
        setNumberText()
    }

    fun minus() {
        if (number.value > 0) {
            number.value -= 1
            setNumberText()
        }
    }

    fun reset() {
        number.value = 0
        setNumberText()
    }

    fun setValue(str: String) {
        str.transferStringToInt().onSuccess {
            number.value = it
            setNumberText()
        }.onFailure {
            number.value = 0
            setNumberText()
        }
    }

    fun init(context: Context) {
        number.value = context.getSharedPreferences("key", 0).getInt("number", 0)
        setNumberText()
    }

    private fun setNumberText() {
        text.value = number.value.toString()
    }

    fun saveNumber(context: Context) {
        val edit = context.getSharedPreferences("key", 0).edit()
        edit.putInt("number", number.value)
        edit.apply()
    }
}