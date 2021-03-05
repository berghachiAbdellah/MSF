package com.berghachi.msf.domain.usecase

import com.berghachi.msf.datasource.model.Resource
import com.berghachi.msf.domain.model.DeleteUserResponse
import com.berghachi.msf.domain.model.DeveloppersResponse
import com.berghachi.msf.domain.repository.Repository




class UsersUseCase(private val repository: Repository) {

    /**
     * Get Devs
     *
     * @return
     */
    suspend fun get(): Resource<DeveloppersResponse> =
        repository.getDevs()

    /**
     * Get Devs
     *
     * @return
     */
    suspend fun deleteUser(id:String): Resource<DeleteUserResponse> =
        repository.deleteDevs(id)

}