package com.berghachi.msf.data.remote

import com.berghachi.msf.datasource.model.Resource
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Error codes enum
 */
enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

/**
 * Response handler class used with conjunction
 * [Resource]
 */
open class ResponseHandler {

    /**
     * Handle successful response
     * @param data response data
     */
    fun <T : Any> handleSuccess(data: T?): Resource<T> {
        return Resource.success(data)
    }

    /**
     * Handle errors
     * @param e exception
     */
    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(
                getErrorMessage(ErrorCodes.SocketTimeOut.code), null
            )
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    /**
     * Get error message
     * @param code error code
     * @return message string
     */
    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorized"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}
