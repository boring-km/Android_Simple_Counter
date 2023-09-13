package com.boring.gangmin.counter.data

import android.content.Context
import com.boring.gangmin.counter.data.utils.transferStringToLong
import kotlinx.coroutines.flow.MutableStateFlow

class CounterNumberRepository {
    private val number = MutableStateFlow(0L)
    val counterNumber = number

    fun add() {
        number.value += 1
    }

    fun minus() {
        if (number.value > 0) {
            number.value -= 1
        }
    }

    fun reset() {
        number.value = 0
    }

    fun setValue(str: String) {
        str.transferStringToLong().onSuccess {
            number.value = it
        }.onFailure {
            number.value = 0
        }
    }

    fun init(context: Context) {
        number.value = context.getSharedPreferences("key", 0).getLong("number", 0)
    }

    fun saveNumber(context: Context) {
        val edit = context.getSharedPreferences("key", 0).edit()
        edit.putLong("number", number.value)
        edit.apply()
    }
}