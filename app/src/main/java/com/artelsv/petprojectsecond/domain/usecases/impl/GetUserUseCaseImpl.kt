package com.artelsv.petprojectsecond.domain.usecases.impl

import com.artelsv.petprojectsecond.domain.UserRepository
import com.artelsv.petprojectsecond.domain.model.User
import com.artelsv.petprojectsecond.domain.usecases.GetUserUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserUseCase {
    override fun invoke(): Single<User> {
        return userRepository.getUser()
    }

    override fun getLocalUser(): User? {
        return userRepository.getLocalUser()
    }

    override fun exit() {
        userRepository.exit()
    }
}