package com.example.learnconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.learnconnect.data.local.entity.CourseEntity
import com.example.learnconnect.data.local.entity.EnrolledCourseEntity

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)

    @Query("SELECT * FROM courses")
    suspend fun getAllCourses(): List<CourseEntity>

    @Query("""
        SELECT c.* 
        FROM courses c 
        INNER JOIN enrolled_courses ec ON c.id = ec.courseId 
        WHERE ec.userId = :userId
    """)
    suspend fun getEnrolledCourses(userId: String): List<CourseEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun enrollCourse(enrolledCourse: EnrolledCourseEntity)

    @Query("SELECT * FROM courses WHERE id = :courseId")
    suspend fun getCourseById(courseId: String): CourseEntity?

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM enrolled_courses 
            WHERE userId = :userId AND courseId = :courseId
        )
    """)
    suspend fun isEnrolled(userId: String, courseId: String): Boolean

    @Query("""
        SELECT progress FROM enrolled_courses 
        WHERE userId = :userId AND courseId = :courseId
    """)
    suspend fun getCourseProgress(userId: String, courseId: String): Float
}