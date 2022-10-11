package com.example.homework4

import java.io.Serializable

data class User(
    val firstName: String?,
    val lastName: String?,
    val username: String?,
    val password: String?
) : Serializable
