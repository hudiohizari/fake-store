package com.hizari.common.extention

import com.hizari.common.data.Result

/**
 * Fake Store - com.hizari.common.extention
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

suspend fun <T : Any, R : Any> handleResult(
    resultCall: suspend () -> Result<T>,
    onError: suspend (Int?, String?, Throwable?) -> Result<R> = { code, message, throwable ->
        Result.Error(code, message, throwable)
    },
    onSuccess: suspend (T) -> Result<R>
): Result<R> {
    return when (val result = resultCall()) {
        is Result.Success -> onSuccess(result.data)
        is Result.Error -> onError(result.code, result.message, result.throwable)
        is Result.Loading -> result
        is Result.Empty -> result
    }
}