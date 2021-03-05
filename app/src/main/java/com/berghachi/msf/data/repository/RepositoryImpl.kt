package com.berghachi.msf.data.repository

import com.berghachi.msf.data.remote.RemoteDataSource
import com.berghachi.msf.data.remote.ResponseHandler
import com.berghachi.msf.datasource.model.Resource
import com.berghachi.msf.domain.model.DeleteUserResponse
import com.berghachi.msf.domain.model.DeveloppersResponse
import com.berghachi.msf.domain.repository.Repository


class RepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val responseHandler: ResponseHandler
) : Repository, BaseDataSource() {


    override suspend fun getDevs(): Resource<DeveloppersResponse> {
        return try {
            val response = remoteDataSource.getDevs()
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun deleteDevs(id: String): Resource<DeleteUserResponse> {
        return try {
            val response = remoteDataSource.deleteDev(id)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }


}