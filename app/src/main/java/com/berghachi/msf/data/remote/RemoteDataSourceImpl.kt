package com.berghachi.msf.data.remote

import com.berghachi.msf.domain.model.DeleteUserResponse
import com.berghachi.msf.domain.model.DeveloppersResponse


class RemoteDataSourceImpl constructor(
    private val api:ApiService
) : RemoteDataSource {


    override suspend fun getDevs(): DeveloppersResponse {
        return api.getDevsAsync().await()
    }

    override suspend fun deleteDev(id: String): DeleteUserResponse {
        return api.deleteDevsAsync(id).await()
    }


}