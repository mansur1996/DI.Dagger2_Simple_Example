package com.example.dagger2_example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2_example.database.entity.UserEntity
import com.example.dagger2_example.repository.UserRepository
import com.example.dagger2_example.utils.NetworkHelper
import com.example.dagger2_example.utils.UserResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val stateFlow = MutableStateFlow<UserResource>(UserResource.Loading)

    init {
        getUsers()
    }

    private fun getUsers() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                userRepository.getUsers()
                    .catch {
                        stateFlow.value = UserResource.Error(it.message ?: "")
                    }.collect {
                        if (it.isSuccessful) {
                            val list = ArrayList<UserEntity>()
                            it.body()?.forEach {
                                val userEntity = UserEntity(it.id, it.name, it.username, it.email)
                                list.add(userEntity)
                            }
                            userRepository.insertUsersToDB(list)
                            stateFlow.value = UserResource.Success(userRepository.getUsersFromDB())
                        } else {

                        }
                    }
            }
        } else {
            stateFlow.value = UserResource.Error("Internet not connected")
        }

    }

    fun getStateFlow(): StateFlow<UserResource> {
        return stateFlow
    }

}