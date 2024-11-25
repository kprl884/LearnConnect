package com.example.learnconnect.domain.di

import android.content.Context
import com.example.learnconnect.data.local.AppDatabase
import com.example.learnconnect.data.local.AuthRepositoryImpl
import com.example.learnconnect.data.local.UserDao
import com.example.learnconnect.data.local.preferences.UserPreferences
import com.example.learnconnect.domain.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideUserDao(context: Context): UserDao {
        return AppDatabase.getInstance(context).userDao()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(userDao: UserDao, userPreferences: UserPreferences): AuthRepository {
        return AuthRepositoryImpl(userDao, userPreferences)
    }
}