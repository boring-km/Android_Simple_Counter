package com.boring.gangmin.counter.utils

// transfer String to Int function
fun String.transferStringToInt(): Result<Int> {
    return try {
        Result.success(this.toInt())
    } catch (e: Exception) {
        Result.failure(e)
    }
}