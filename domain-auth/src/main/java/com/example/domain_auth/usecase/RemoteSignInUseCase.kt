package com.example.domain_auth.usecase

import com.example.domain_auth.param.LoginParam
import com.example.domain_auth.repository.UserRepository
import javax.inject.Inject

class RemoteSignInUseCase @Inject constructor(
    private val userRepository: UserRepository
): UseCase<LoginParam, Unit>() {
    override suspend fun execute(data: LoginParam) {
        userRepository.login(data)
    }
}