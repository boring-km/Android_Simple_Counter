package com.boring.gangmin.counter.data.utils

// transfer String to Int function
fun String.transferStringToLong(): Result<Long> {
    return try {
        Result.success(this.toLong())
    } catch (e: Exception) {
        Result.failure(e)
    }
}