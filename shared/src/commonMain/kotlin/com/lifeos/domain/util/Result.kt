package com.lifeos.domain.util

/**
 * A wrapper for data that represents the result of an operation.
 * Used throughout the app for consistent error handling.
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception, val message: String? = null) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}

/**
 * Extension function to safely get data from a Result or null if Error/Loading
 */
fun <T> Result<T>.getOrNull(): T? = when (this) {
    is Result.Success -> data
    else -> null
}

/**
 * Extension function to check if Result is Success
 */
fun <T> Result<T>.isSuccess(): Boolean = this is Result.Success

/**
 * Extension function to check if Result is Error
 */
fun <T> Result<T>.isError(): Boolean = this is Result.Error

/**
 * Extension function to check if Result is Loading
 */
fun <T> Result<T>.isLoading(): Boolean = this is Result.Loading
