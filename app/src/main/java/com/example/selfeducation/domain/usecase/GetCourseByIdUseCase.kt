package com.example.selfeducation.domain.usecase

import androidx.lifecycle.LiveData
import com.example.selfeducation.domain.entity.Course
import com.example.selfeducation.domain.repository.CoursesRepository

class GetCourseByIdUseCase(private val coursesRepository: CoursesRepository) {
    operator fun invoke(courseId: Int): LiveData<Course> {
        return coursesRepository.getCourseById(courseId)
    }
}