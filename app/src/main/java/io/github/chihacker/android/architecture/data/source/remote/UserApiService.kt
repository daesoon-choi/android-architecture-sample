package io.github.chihacker.android.architecture.data.source.remote

//API 에서 호출한거라고 가정
class UserApiService {
    fun getUser(): UserResponse {
        return UserResponse(
            "111111",
            "daesoon",
            10,
            12344556
        )
    }
}