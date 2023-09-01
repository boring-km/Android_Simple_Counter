package com.boring.gangmin.counter.view

import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.MotionEvent
import android.view.SoundEffectConstants
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
            MainScreen(viewModel, onTouch = {
                (getSystemService(Context.AUDIO_SERVICE) as AudioManager).playSoundEffect(AudioManager.FX_KEY_CLICK, 1f)
            }, onVibrate = {
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
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