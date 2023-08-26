package com.boring.gangmin.counter.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this, CounterViewModelFactory())[CounterViewModel::class.java]

        setContent {
            MainScreen(viewModel)
        }
    }
}