package com.berghachi.msf.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berghachi.msf.datasource.model.ApiStatus
import com.berghachi.msf.domain.model.DeleteUserResponse
import com.berghachi.msf.domain.model.DeveloppersResponse
import com.berghachi.msf.domain.model.User
import com.berghachi.msf.domain.usecase.UsersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(private val getUsersUseCase: UsersUseCase) :
    ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users


    private val _userDeleted = MutableLiveData<Boolean?>()
    val userDeleted: LiveData<Boolean?>
        get() = _userDeleted

    private val _status = MutableLiveData<ApiStatus>()

    /**
     * The external immutable LiveData for the request status
     */
    val status: LiveData<ApiStatus>
        get() = _status


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getDevs() {
        _status.value = ApiStatus.LOADING
        coroutineScope.launch {
            val usersResponse = getUsersUseCase.get()
            _status.value = usersResponse.status

            when (_status.value) {
                ApiStatus.DONE -> {
                    if (usersResponse.data is DeveloppersResponse) {

                        _users.value = usersResponse.data.list.sortedBy { user->user.name }

                    } else {
                        _users.value = ArrayList()
                    }

                }

                ApiStatus.ERROR -> {
                    _users.value = ArrayList()
                }
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun deleteUser(id: String) {
        coroutineScope.launch {
            val deleteUsersResponse = getUsersUseCase.deleteUser(id)
            _status.value = deleteUsersResponse.status

            when (_status.value) {
                ApiStatus.DONE -> {
                    if (deleteUsersResponse.data is DeleteUserResponse) {

                        _userDeleted.value = false

                    } else {
                        _userDeleted.value = false
                    }

                }

                ApiStatus.ERROR -> {
                    _userDeleted.value = false
                }
            }
        }
    }

}