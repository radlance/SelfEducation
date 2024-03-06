package com.example.selfeducation.domain.usecase

import androidx.lifecycle.LiveData
import com.example.selfeducation.domain.entity.Course
import com.example.selfeducation.domain.repository.CoursesRepository

class GetCourseListUseCase(private val coursesRepository: CoursesRepository) {
    operator fun invoke(): LiveData<List<Course>> {
        return coursesRepository.getCourseList()
    }
}