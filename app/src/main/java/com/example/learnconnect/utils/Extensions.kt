package com.example.learnconnect.utils

import com.example.learnconnect.domain.User
import com.example.learnconnect.data.local.entity.UserEntity

object Extensions {
    fun UserEntity.toDomain() = User(
        id = id,
        email = email,
        password = password
    )
}