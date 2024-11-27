package com.example.learnconnect.domain.model

data class Video(
    val id: String,
    val title: String,
    val onlineUrl: String,
    val offlineUrl: String? = null,
    val thumbnailUrl: String? = null,
    val duration: Long = 0L
)