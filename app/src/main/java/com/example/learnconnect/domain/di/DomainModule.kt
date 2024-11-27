package com.example.learnconnect.domain.di

import com.example.learnconnect.data.local.impl.AuthRepositoryImpl
import com.example.learnconnect.data.local.impl.CourseRepositoryImpl
import com.example.learnconnect.data.local.dao.CourseDao
import com.example.learnconnect.data.local.dao.FavoriteCoursesDao
import com.example.learnconnect.data.local.dao.UserDao
import com.example.learnconnect.data.local.preferences.UserPreferences
import com.example.learnconnect.domain.repository.AuthRepository
import com.example.learnconnect.domain.repository.CourseRepository
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
    fun providesCourseRepository(
        courseDao: CourseDao,
        favoriteCoursesDao: FavoriteCoursesDao,
        userPreferences: UserPreferences
    ): CourseRepository {
        return CourseRepositoryImpl(
            courseDao,
            favoriteCoursesDao = favoriteCoursesDao,
            userPreferences
        )
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        userDao: UserDao,
        courseDao: CourseDao,
        userPreferences: UserPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(
            userDao,
            courseDao = courseDao,
            userPreferences
        )
    }
}