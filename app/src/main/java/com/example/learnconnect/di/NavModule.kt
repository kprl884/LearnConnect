package com.example.learnconnect.di

import com.example.learnconnect.core.navigation.NavProvider
import com.example.learnconnect.core.navigation.NavProviderItem
import com.example.learnconnect.ui.auth.nav.LoginNavRegisterer
import com.example.learnconnect.ui.auth.nav.RegisterNavRegisterer
import com.example.learnconnect.ui.courses.MyCoursesNavRegisterer
import com.example.learnconnect.ui.home.HomeNavRegisterer
import com.example.learnconnect.ui.profile.nav.ProfileNavRegisterer
import com.example.learnconnect.ui.splash.nav.SplashNavRegisterer
import com.example.learnconnect.ui.video.nav.VideoNavRegisterer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavModule {

    @Provides
    @Singleton
    fun provideNavProvider(): NavProvider = NavProvider(
        screens = listOf(
            NavProviderItem(screen = SplashNavRegisterer()),
            NavProviderItem(screen = LoginNavRegisterer()),
            NavProviderItem(screen = RegisterNavRegisterer()),
            NavProviderItem(screen = HomeNavRegisterer()),
            NavProviderItem(screen = MyCoursesNavRegisterer()),
            NavProviderItem(screen = ProfileNavRegisterer()),
            NavProviderItem(screen = VideoNavRegisterer()),
        )
    )
}