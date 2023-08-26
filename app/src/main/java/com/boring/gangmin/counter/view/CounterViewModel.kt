package com.boring.gangmin.counter.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boring.gangmin.counter.utils.transferStringToInt
import kotlinx.coroutines.flow.MutableStateFlow

class CounterViewModel: ViewModel() {
    private val number = MutableStateFlow(0)
    val text = MutableStateFlow("0")

    fun add() {
        number.value += 1
        text.value = number.value.toString()
    }

    fun minus() {
        if (number.value > 0) {
            number.value -= 1
            text.value = number.value.toString()
        }
    }

    fun reset() {
        number.value = 0
        text.value = number.value.toString()
    }

    fun setValue(str: String) {
        str.transferStringToInt().onSuccess {
            number.value = it
            text.value = number.value.toString()
        }.onFailure {
            number.value = 0
            text.value = number.value.toString()
        }
    }
}

class CounterViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CounterViewModel() as T
        }
        throw IllegalAccessException("Unkown Viewmodel Class")
    }
}