

package com.berghachi.msf.domain.repository

import com.berghachi.msf.datasource.model.Resource
import com.berghachi.msf.domain.model.DeleteUserResponse
import com.berghachi.msf.domain.model.DeveloppersResponse


interface Repository {

    suspend fun getDevs() :  Resource<DeveloppersResponse>

    suspend fun deleteDevs(id:String) :  Resource<DeleteUserResponse>

}
