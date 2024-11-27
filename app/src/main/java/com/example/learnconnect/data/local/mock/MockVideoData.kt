package com.example.learnconnect.data.local.mock

import com.example.learnconnect.domain.model.Video

object MockVideoData {
    val videos = listOf(
        Video(
            id = "1",
            title = "Kotlin ile Android Programlama",
            onlineUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            offlineUrl = "asset:///videos/kotlin_basics.mp4"
        ),
        Video(
            id = "2",
            title = "iOS Swift Temel Eğitim",
            onlineUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            offlineUrl = "asset:///videos/compose_basics.mp4"
        ),
        Video(
            id = "3",
            title = "Flutter ile Cross-Platform Geliştirme",
            onlineUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            offlineUrl = "asset:///videos/compose_basics.mp4"
        ),
        Video(
            id = "4",
            title = "React Native Masterclass",
            onlineUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            offlineUrl = "asset:///videos/compose_basics.mp4"
        ),
        Video(
            id = "5",
            title = "Unity ile Oyun Programlama",
            onlineUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            offlineUrl = "asset:///videos/compose_basics.mp4"
        )
    )
}