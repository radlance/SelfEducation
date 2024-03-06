package com.example.selfeducation.domain.usecase

import androidx.lifecycle.LiveData
import com.example.selfeducation.domain.entity.Course
import com.example.selfeducation.domain.repository.CoursesRepository

class SearchCoursesUseCase(private val coursesRepository: CoursesRepository) {
    operator fun invoke(query: String): List<Course> {
        return coursesRepository.searchCourses(query)
    }
}