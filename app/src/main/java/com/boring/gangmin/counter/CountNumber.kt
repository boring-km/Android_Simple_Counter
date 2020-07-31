package com.boring.gangmin.counter

import android.text.Editable

class CountNumber(internal var number: Int) {

    fun plus() {
        number++
    }

    fun setZero() {
        number = 0
    }

    fun isPositive(): Boolean {
        return number > 0
    }

    fun minus() {
        if (isPositive()) number--
    }

    fun setNumber(text: Editable?) {
        number = Integer.parseInt(text.toString())
    }

}