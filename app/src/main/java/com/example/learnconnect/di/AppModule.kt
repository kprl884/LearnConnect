package com.example.learnconnect.di

import android.content.Context
import com.example.learnconnect.LearnConnectApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    @BaseSDK
    fun provideContext(application: LearnConnectApplication): Context {
        return application.applicationContext
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseSDK
}