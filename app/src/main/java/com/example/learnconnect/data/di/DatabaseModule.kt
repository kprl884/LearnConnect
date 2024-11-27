package com.example.learnconnect.data.di

import android.content.Context
import androidx.room.Room
import com.example.learnconnect.data.local.AppDatabase
import com.example.learnconnect.data.local.dao.CourseDao
import com.example.learnconnect.data.local.dao.FavoriteCoursesDao
import com.example.learnconnect.data.local.dao.UserDao
import com.example.learnconnect.data.local.dao.VideoProgressDao
import com.example.learnconnect.data.local.preferences.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database"
    ).build()


    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(context: Context): UserDao {
        return AppDatabase.getInstance(context).userDao()
    }

    @Provides
    @Singleton
    fun provideCourseDao(context: Context): CourseDao {
        return AppDatabase.getInstance(context).courseDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteCoursesDao(context: Context): FavoriteCoursesDao {
        return AppDatabase.getInstance(context).favoriteCoursesDao()
    }

    @Provides
    @Singleton
    fun provideVideoProgressDao(context: Context): VideoProgressDao {
        return AppDatabase.getInstance(context).videoProgressDao()
    }
}