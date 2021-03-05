package com.berghachi.msf.data.repository


import com.berghachi.msf.datasource.model.Resource
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(
                    body
                )
            }
            response.errorBody()?.source()
            return error(getErrorMessage(response.code()))
        } catch (throwable: Throwable) {
            return when (throwable) {
                is HttpException -> Resource.error(getErrorMessage(throwable.code()), null)

                else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
            }


        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.e(message)
        return Resource.error(message)
    }


    private fun getErrorMessage(code: Int): String {
        return when (code) {
            401 -> "Unauthorized"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }


}