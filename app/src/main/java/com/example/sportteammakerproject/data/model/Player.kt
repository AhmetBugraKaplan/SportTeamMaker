package com.example.sportteammakerproject.data.model

data class Player(
    val id: Int = 0,
    val name: String = "",
    val lastName: String = "",
    val position: String = "",
    val age: Int = 0,
    val averagePoint: Float = 0f,
    val admin: Boolean = false
)