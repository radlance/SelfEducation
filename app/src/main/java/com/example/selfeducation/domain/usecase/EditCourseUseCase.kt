package com.example.selfeducation.domain.usecase

import com.example.selfeducation.domain.entity.Course
import com.example.selfeducation.domain.repository.CoursesRepository

class EditCourseUseCase(private val coursesRepository: CoursesRepository) {
    suspend operator fun invoke(course: Course) {
        coursesRepository.editCourse(course)
    }
}