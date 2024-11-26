package com.example.learnconnect.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learnconnect.data.local.dao.CourseDao
import com.example.learnconnect.data.local.dao.UserDao
import com.example.learnconnect.data.local.entity.UserEntity
import com.example.learnconnect.data.local.dao.VideoProgressDao
import com.example.learnconnect.data.local.entity.CourseEntity
import com.example.learnconnect.data.local.entity.EnrolledCourseEntity
import com.example.learnconnect.data.local.entity.VideoProgressEntity

@Database(
    entities = [UserEntity::class, VideoProgressEntity::class, CourseEntity::class, EnrolledCourseEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun videoProgressDao(): VideoProgressDao
    abstract fun courseDao(): CourseDao

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