plugins {
    alias(libs.plugins.androidApplicationPlugin)
    alias(libs.plugins.kotlinAndroidPlugin)
    alias(libs.plugins.hiltPlugin)
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.example.learnconnect"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.learnconnect"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(libs.bundles.moshiDependencies)
    api(libs.hiltAndroid)
    ksp(libs.hiltAndroidCompiler)
    api(libs.bundles.lifecycleDependencies)
    api(libs.bundles.coroutinesDependencies)
    api(libs.hiltNavCompose)
    api(libs.navCompose)
    api(libs.navCommon)
    api(libs.kotlinSerializationJson)
    api(libs.roomKtx)
    api(libs.roomRuntime)
    ksp(libs.roomCompiler)
    api(libs.bundles.androidxDependencies)
    api(libs.bundles.composeDependencies)
    api(libs.coil)
    debugImplementation(libs.composeUiTooling)
    implementation(libs.google.fonts)
    implementation (libs.lottie)
    implementation (libs.lottie.compose)
    implementation (libs.gson)
    implementation (libs.androidx.datastore.preferences)
    implementation (libs.androidx.datastore.preferences.core)
    implementation (libs.androidx.media3.exoplayer)
    implementation (libs.androidx.media3.ui)
    implementation (libs.androidx.media3.common)
}