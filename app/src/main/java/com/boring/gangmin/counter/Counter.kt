package com.boring.gangmin.counter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.app.Activity

class Counter : AppCompatActivity() {

    private var pressTime:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()
        var number = 0
        var vibrator:Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        var imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        countNum.imeOptions = EditorInfo.IME_ACTION_DONE
        countNum.setText(number.toString())

        countNum.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                number = try {
                    Integer.parseInt(countNum.text.toString())
                } catch (e:Exception) {
                    Toast.makeText(this, "그러다가 오류납니다.", Toast.LENGTH_SHORT).show()
                    0
                } finally {
                    imm.hideSoftInputFromWindow(countNum.windowToken, 0)
                }
            } else {
                return@OnEditorActionListener true
            }
            false
        })

        plus_btn.setOnClickListener {
            vibrator.vibrate(3)
            try {
                number = if(countNum.text.toString() == ""){
                    0
                } else
                    Integer.parseInt(countNum.text.toString())
            } catch (e:Exception){
                e.printStackTrace()
            }

            number++
            countNum.setText(number.toString())
        }
        minus_btn.setOnClickListener{

            number = if(countNum.text.toString() == ""){
                0
            } else
                Integer.parseInt(countNum.text.toString())

            if(number > 0){
                number = Integer.parseInt(countNum.text.toString())
                number--
            }
            countNum.setText(number.toString())
        }
        reset_btn.setOnClickListener {
            number = 0
            countNum.setText(number.toString())
        }
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() - pressTime < 2000) {
            finishAffinity()
            return
        }
        Toast.makeText(this, "한 번 더 취소하시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        pressTime = System.currentTimeMillis()
    }
}
