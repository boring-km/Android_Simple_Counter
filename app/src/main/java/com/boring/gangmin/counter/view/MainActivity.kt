package com.boring.gangmin.counter.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: CounterViewModel

    private val vibratorManager by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        } else {
            @Suppress("DEPRECATION")
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, CounterViewModelFactory())[CounterViewModel::class.java]

        viewModel.init(applicationContext)

        setContent {
            MainScreen(viewModel, onVibrate = {
                vibrate()
            })
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveNumber(applicationContext)
    }

    override fun onStop() {
        super.onStop()
        cancelVibration()
    }

    private fun cancelVibration() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (vibratorManager as VibratorManager).cancel()
        } else {
            (vibratorManager as Vibrator).cancel()
        }
    }

    fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (vibratorManager as VibratorManager).vibrate(
                CombinedVibration.createParallel(
                    VibrationEffect.createOneShot(3, VibrationEffect.DEFAULT_AMPLITUDE)
                )
            )
        } else {
            @Suppress("DEPRECATION")
            (vibratorManager as Vibrator).vibrate(3)
        }
    }
}