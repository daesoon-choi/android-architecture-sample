package io.github.chihacker.android.architecture.ui.main

import io.github.chihacker.android.architecture.common.exception.UserNotExistException
import io.github.chihacker.android.architecture.ui.main.model.User

sealed class MainViewState {
    object Loading: MainViewState()
    data class Content(val user: User): MainViewState()
    object Error: MainViewState()
}

@Throws(UserNotExistException::class)
fun MainViewState.getUser(): User {
    return when(this) {
        is MainViewState.Loading -> throw UserNotExistException()
        is MainViewState.Error -> throw UserNotExistException()
        is MainViewState.Content -> this.user
    }
}