package com.example.learnconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learnconnect.data.local.entity.FavoriteCourseEntity

@Dao
interface FavoriteCoursesDao {
    @Query("SELECT * FROM favorite_courses")
    suspend fun getAllFavorites(): List<FavoriteCourseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(course: FavoriteCourseEntity)

    @Delete
    suspend fun removeFromFavorites(course: FavoriteCourseEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_courses WHERE courseId = :courseId)")
    suspend fun isFavorite(courseId: String): Boolean
}