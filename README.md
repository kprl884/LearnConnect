# LearnConnect - Online Learning Platform
LearnConnect, kullanıcıların eğitim içeriklerine kolayca erişebileceği, kurslar alabileceği ve öğrenme deneyimini takip edebileceği modern bir eğitim platformudur.
🚀 Özellikler

Kurs Yönetimi

Kursları görüntüleme ve kayıt olma
Favori kurslara ekleme
Kurs ilerleme takibi


Video Player

Online/Offline video oynatma desteği
İlerleme kaydetme
Kaldığın yerden devam etme


Kullanıcı Deneyimi

Dark/Light tema desteği
Kişiselleştirilmiş profil
Sezgisel kullanıcı arayüzü



🛠️ Teknolojiler

Mimari: Clean Architecture + MVVM
UI Framework: Jetpack Compose
Dependency Injection: Hilt
Local Storage: Room Database
State Management: Flow & StateFlow
Video Player: ExoPlayer
Navigation: Jetpack Navigation
Theme: Material Design 3

📋 Gereksinimler

Android Studio Hedgehog | 2023.1.1
Minimum SDK 24
Target SDK 34
Kotlin 1.9.0

🔧 Kurulum

Projeyi klonlayın:

bashCopygit clone https://github.com/yourusername/LearnConnect.git

Android Studio'da açın ve bağımlılıkların yüklenmesini bekleyin
local.properties dosyasında gerekli API anahtarlarını tanımlayın:

propertiesCopysdk.dir=/Users/yourusername/Library/Android/sdk

Uygulamayı çalıştırın:

Run > Run 'app'
veya Shift + F10



🏗️ Proje Yapısı
Copyapp/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   └── entity/
│   └── repository/
├── domain/
│   ├── model/
│   └── usecase/
├── ui/
│   ├── course/
│   ├── profile/
│   └── video/
└── utils/
🎯 Mimari
Proje Clean Architecture prensiplerini takip eder:

Data Layer: Room Database, DataStore ve API ile iletişim
Domain Layer: Use case'ler ve business logic
UI Layer: MVVM pattern ile kullanıcı arayüzü

🎨 Tasarım Desenleri

Repository Pattern
Dependency Injection
Observer Pattern (Flow)
Factory Pattern
Builder Pattern

🔄 State Management
kotlinCopydata class ProfileUiState(
    val userName: String = "",
    val email: String = "",
    val enrolledCourses: List<CoursePreview> = emptyList(),
    val favoriteCourses: List<CoursePreview> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
🎥 Video Player Özellikleri

Progress takibi
Online/Offline mod
Otomatik ilerleme kaydı
Özelleştirilmiş kontroller

🌟 Bonus Özellikler

Offline Modu

Videoları indirip offline izleme
İlerlemeyi local'de saklama


Dark/Light Tema

Sistem teması ile senkronizasyon
Manuel tema değiştirme


Kurs Favorileme

Favori kursları kaydetme
Hızlı erişim
