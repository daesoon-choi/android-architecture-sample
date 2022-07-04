package io.github.chihacker.android.architecture.data.repositories

import io.github.chihacker.android.architecture.data.source.remote.UserApiService
import io.github.chihacker.android.architecture.data.source.remote.UserResponse

class UserRepository(
    private val userApiService: UserApiService
) {
    fun getUser(): UserResponse {
        return userApiService.getUser()
    }
}
