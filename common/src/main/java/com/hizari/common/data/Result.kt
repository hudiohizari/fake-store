package com.hizari.common.data

/**
 * Fake Store - com.hizari.common.data
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

sealed class Result<out T> {

    data class Error(
        val code: Int? = null,
        val message: String? = null,
        val throwable: Throwable? = null
    ) : Result<Nothing>() {
        fun asMessage(): String {
            val message = message ?: throwable?.message ?: "Unknown error"
            return if (code == null) message else "$message (Code: $code)"
        }

        fun asThrowable() = throwable ?: Exception(asMessage())
    }

    data object Empty : Result<Nothing>()

    data object Loading : Result<Nothing>()

    data class Success<T>(val data: T) : Result<T>()

    fun error() = this is Error
    fun empty() = this is Empty
    fun loading() = this is Loading
    fun success() = this is Success

    inline fun <T : Any, R> Result<T>.dataOr(defaultValue: R, transform: (T) -> R): R {
        return if (this is Success) {
            transform(data)
        } else {
            defaultValue
        }
    }

}