package com.berghachi.msf.data.remote

import com.berghachi.msf.domain.model.DeleteUserResponse
import com.berghachi.msf.domain.model.DeveloppersResponse


interface RemoteDataSource {


    suspend fun getDevs() : DeveloppersResponse

    suspend fun deleteDev(id:String): DeleteUserResponse

}