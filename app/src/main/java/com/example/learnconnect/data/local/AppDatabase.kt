package com.example.learnconnect.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learnconnect.data.local.dao.CourseDao
import com.example.learnconnect.data.local.dao.FavoriteCoursesDao
import com.example.learnconnect.data.local.dao.UserDao
import com.example.learnconnect.data.local.entity.UserEntity
import com.example.learnconnect.data.local.dao.VideoProgressDao
import com.example.learnconnect.data.local.entity.CourseEntity
import com.example.learnconnect.data.local.entity.EnrolledCourseEntity
import com.example.learnconnect.data.local.entity.FavoriteCourseEntity
import com.example.learnconnect.data.local.entity.VideoProgress

@Database(
    entities = [UserEntity::class, FavoriteCourseEntity::class, VideoProgress::class, CourseEntity::class, EnrolledCourseEntity::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun videoProgressDao(): VideoProgressDao
    abstract fun courseDao(): CourseDao
    abstract fun favoriteCoursesDao(): FavoriteCoursesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "learnconnect.db"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }
}