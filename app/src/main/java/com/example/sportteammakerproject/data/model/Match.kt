package com.example.sportteammakerproject.data.model

data class Match(
    val matchid : Int = 0,
    val adminID: Int = 0,
    val numberOfPlayer: Int = 0,
    val matchDay: Boolean = false,
    val matchHour: Boolean = false,
    val team1Name: String = "",
    val team2Name: String = "",
    val team1Score: Int = 0,
    val team2Score: Int = 0
)