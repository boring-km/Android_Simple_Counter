package com.boring.gangmin.counter

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class Counter : AppCompatActivity() {

    private var pressTime: Long = 0
    private var number: CountNumber = CountNumber(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number = CountNumber(getSharedPreferences("key", 0).getInt("number", 0))
        setCountNumberActionListener(getInputMethodManager())

        setPlusButtonClickListener()
        setMinusButtonClickListener()
        setResetButtonClickListener()
    }

    private fun setResetButtonClickListener() {
        resetButton.setOnClickListener {
            number.setZero()
            setCountNumber()
        }
    }

    private fun setMinusButtonClickListener() {
        minusButton.setOnClickListener {
            if (isIntNumber()) {
                getNotEmptyCountNumber()
                number.setNumber(countNumber.text)
                number.minus()
            }
            setCountNumber()
        }
    }

    @Suppress("DEPRECATION")
    private fun setPlusButtonClickListener() {
        plusButton.setOnClickListener {
            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(3)
            if (isIntNumber()) {
                getNotEmptyCountNumber()
                number.setNumber(countNumber.text)
                number.plus()
            }
            setCountNumber()
        }
    }

    private fun getNotEmptyCountNumber(): Int {
        return if (countNumber.text.toString().isEmpty()) 0
        else Integer.parseInt(countNumber.text.toString())
    }

    private fun isIntNumber(): Boolean {
        try {
            Integer.parseInt(countNumber.text.toString())
        } catch (e: NumberFormatException) {
            Toast.makeText(applicationContext, "정수 범위에서 이용해 주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun setCountNumberActionListener(inputMethodManager: InputMethodManager) {
        countNumber.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isIntNumber()) number.setNumber(countNumber.text)
                inputMethodManager.hideSoftInputFromWindow(countNumber.windowToken, 0)
            } else {
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun getInputMethodManager(): InputMethodManager {
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        countNumber.imeOptions = EditorInfo.IME_ACTION_DONE
        setCountNumber()
        return inputMethodManager
    }

    private fun setCountNumber() {
        countNumber.setText(number.number.toString())
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - pressTime < 2000) {
            finishAffinity()
            return
        }
        Toast.makeText(this, "한 번 더 취소하시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        pressTime = System.currentTimeMillis()
    }

    override fun onStop() {
        super.onStop()
        (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).cancel()
        saveNumber()
    }

    private fun saveNumber() {
        val edit = getSharedPreferences("key", 0).edit()
        edit.putInt("number", number.number)
        edit.apply()
    }
}
