package com.berghachi.msf.datasource.model

data class Resource<out T>(val status: ApiStatus, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(ApiStatus.DONE, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(ApiStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(ApiStatus.LOADING, data, null)
        }
    }
}
