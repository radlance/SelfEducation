package com.example.selfeducation.domain.repository

import androidx.lifecycle.LiveData
import com.example.selfeducation.domain.entity.Course
import com.example.selfeducation.domain.entity.SortType

interface CoursesRepository {
    suspend fun addCourse(course: Course)
    fun getCourseById(courseId: Int): LiveData<Course>
    fun getCourseList(): LiveData<List<Course>>
    fun searchCourses(query: String): List<Course>
    fun sortCourses(sortType: SortType): LiveData<List<Course>>
    suspend fun editCourse(course: Course)
}