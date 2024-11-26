package com.example.learnconnect.data.local.mock

import com.example.learnconnect.data.local.entity.CourseEntity

object MockCourseData {
   fun getMockCourses() = listOf(
       CourseEntity(
           id = "1",
           title = "Kotlin ile Android Programlama",
           description = "Modern Android uygulama geliştirme tekniklerini öğrenin. Jetpack Compose, MVVM, Clean Architecture ve daha fazlası.",
           thumbnailUrl = "https://example.com/android.jpg",
           instructorName = "Ahmet Yılmaz",
           duration = "12 saat"
       ),
       CourseEntity(
           id = "2", 
           title = "iOS Swift Temel Eğitim",
           description = "Swift programlama dili ile iOS uygulama geliştirmeyi sıfırdan öğrenin. SwiftUI, UIKit ve temel iOS konseptleri.",
           thumbnailUrl = "https://example.com/ios.jpg",
           instructorName = "Mehmet Demir",
           duration = "15 saat"
       ),
       CourseEntity(
           id = "3",
           title = "Flutter ile Cross-Platform Geliştirme",
           description = "Tek kod tabanıyla hem Android hem iOS uygulamaları geliştirin. Widget sistemi, state management ve Firebase entegrasyonu.",
           thumbnailUrl = "https://example.com/flutter.jpg",
           instructorName = "Zeynep Kaya",
           duration = "20 saat"
       ),
       CourseEntity(
           id = "4",
           title = "React Native Masterclass",
           description = "JavaScript bilginizi mobile taşıyın. Component yapısı, hooks, ve native module kullanımı.",
           thumbnailUrl = "https://example.com/react.jpg",
           instructorName = "Can Yıldız",
           duration = "18 saat"
       ),
       CourseEntity(
           id = "5",
           title = "Unity ile Oyun Programlama",
           description = "3D oyun geliştirmenin temellerini öğrenin. Physics engine, animation system ve game logic.",
           thumbnailUrl = "https://example.com/unity.jpg",
           instructorName = "Ayşe Şahin",
           duration = "25 saat"
       )
   )
}