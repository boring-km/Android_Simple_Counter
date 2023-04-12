package com.boring.gangmin.counter

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.boring.gangmin.counter.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class Counter : AppCompatActivity() {

    private var pressTime: Long = 0
    private var number: CountNumber = CountNumber(0)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        number = CountNumber(getSharedPreferences("key", 0).getInt("number", 0))
        setCountNumberActionListener(getInputMethodManager())

        setPlusButtonClickListener()
        setMinusButtonClickListener()
        setResetButtonClickListener()
    }

    private fun setResetButtonClickListener() {
        binding.resetButton.setOnClickListener {
            number.setZero()
            setCountNumber()
        }
    }

    private fun setMinusButtonClickListener() {
        binding.minusButton.setOnClickListener {
            if (isIntNumber()) {
                getNotEmptyCountNumber()
                number.setNumber(binding.countNumber.text)
                number.minus()
            }
            setCountNumber()
        }
    }

    @Suppress("DEPRECATION")
    private fun setPlusButtonClickListener() {
        binding.plusButton.setOnClickListener {
            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(3)
            if (isIntNumber()) {
                getNotEmptyCountNumber()
                number.setNumber(binding.countNumber.text)
                number.plus()
            }
            setCountNumber()
        }
    }

    private fun getNotEmptyCountNumber(): Int {
        return if (binding.countNumber.text.toString().isEmpty()) 0
        else Integer.parseInt(binding.countNumber.text.toString())
    }

    private fun isIntNumber(): Boolean {
        try {
            Integer.parseInt(binding.countNumber.text.toString())
        } catch (e: NumberFormatException) {
            Toast.makeText(applicationContext, "정수 범위에서 이용해 주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun setCountNumberActionListener(inputMethodManager: InputMethodManager) {
        binding.countNumber.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isIntNumber()) number.setNumber(binding.countNumber.text)
                inputMethodManager.hideSoftInputFromWindow(binding.countNumber.windowToken, 0)
            } else {
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun getInputMethodManager(): InputMethodManager {
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.countNumber.imeOptions = EditorInfo.IME_ACTION_DONE
        setCountNumber()
        return inputMethodManager
    }

    private fun setCountNumber() {
        binding.countNumber.setText(number.number.toString())
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
