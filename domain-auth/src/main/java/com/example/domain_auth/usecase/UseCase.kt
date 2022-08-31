package com.example.domain_auth.usecase

abstract class UseCase<I, O> {
    abstract suspend fun execute(data: I): O
}