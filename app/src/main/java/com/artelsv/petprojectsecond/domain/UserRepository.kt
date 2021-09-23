package com.artelsv.petprojectsecond.domain

import com.artelsv.petprojectsecond.data.network.model.RequestTokenResponse
import com.artelsv.petprojectsecond.data.network.model.SessionResponse
import io.reactivex.Single

interface UserRepository {

    fun createRequestToken() : Single<RequestTokenResponse>
    fun createSession(requestToken: String) : Single<SessionResponse>
    fun createSessionWithUser(requestToken: String, login: String, password: String) : Single<SessionResponse>
}