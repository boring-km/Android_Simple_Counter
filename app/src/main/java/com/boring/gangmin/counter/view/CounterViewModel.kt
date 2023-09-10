package com.boring.gangmin.counter.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boring.gangmin.counter.data.NumberRepository

class CounterViewModel : ViewModel() {

    private val repository = NumberRepository()
    val text = repository.text

    fun add() = repository.add()

    fun minus() = repository.minus()

    fun reset() = repository.reset()

    fun setValue(str: String) = repository.setValue(str)

    fun init(context: Context) = repository.init(context)

    fun saveNumber(context: Context) = repository.saveNumber(context)
}

class CounterViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CounterViewModel() as T
        }
        throw IllegalAccessException("Unkown ViewModel Class")
    }
}