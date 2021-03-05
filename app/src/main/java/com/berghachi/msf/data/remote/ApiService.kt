package com.berghachi.msf.data.remote

import com.berghachi.msf.domain.model.DeleteUserResponse
import com.berghachi.msf.domain.model.DeveloppersResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET("/trombi")
    fun getDevsAsync():
            Deferred<DeveloppersResponse>

    @DELETE("/trombi")
    fun deleteDevsAsync(@Query("delete")id:String):
            Deferred<DeleteUserResponse>

}


