package com.example.selfeducation.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.selfeducation.domain.entity.Course

@Dao
interface SelfEducationDao {
    @Query("SELECT * FROM course")
    fun getCourseList(): LiveData<List<Course>>

    @Query("SELECT * FROM course WHERE id =:courseId")
    fun getCourseById(courseId: Int): LiveData<Course>

    @Query("SELECT * FROM course WHERE title LIKE :query OR author LIKE :query")
    fun searchCourses(query: String): List<Course>

    @Query("SELECT * FROM course ORDER BY price DESC")
    fun getCoursesByPriceDesc(): LiveData<List<Course>>

    @Query("SELECT * FROM course ORDER BY price ASC")
    fun getCoursesByPriceAsc(): LiveData<List<Course>>

    @Query("SELECT * FROM course ORDER BY title ASC")
    fun getCoursesByTitleAsc(): LiveData<List<Course>>

    @Query("SELECT * FROM course ORDER BY rating DESC")
    fun getCoursesByRatingDesc(): LiveData<List<Course>>

    @Query("SELECT * FROM course ORDER BY rating ASC")
    fun getCoursesByRatingAsc(): LiveData<List<Course>>

    @Query("SELECT * FROM course ORDER BY people_count DESC")
    fun getCoursesByPeopleCountDesc(): LiveData<List<Course>>

    @Query("SELECT * FROM course ORDER BY people_count ASC")
    fun getCoursesByPeopleCountAsc(): LiveData<List<Course>>

    @Update
    suspend fun editCourse(course: Course)

    @Insert
    suspend fun insertCourse(course: Course)
}