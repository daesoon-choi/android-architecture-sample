package io.github.chihacker.android.architecture.domain

import io.github.chihacker.android.architecture.data.repositories.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    fun invoke(param: Param): Dto {
        val user = userRepository.getUser()
        return Dto(
            user.id,
            user.name,
            user.age,
            user.age + 1
        )
    }

    data class Param(val id: String)
    data class Dto(val id: String, val name: String, val age: Int, val kage: Int)
}