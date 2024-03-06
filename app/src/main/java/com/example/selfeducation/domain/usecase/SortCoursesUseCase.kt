package com.example.selfeducation.domain.usecase

import androidx.lifecycle.LiveData
import com.example.selfeducation.domain.entity.Course
import com.example.selfeducation.domain.entity.SortType
import com.example.selfeducation.domain.repository.CoursesRepository

class SortCoursesUseCase(private val coursesRepository: CoursesRepository) {
    operator fun invoke(sortType: SortType): LiveData<List<Course>> {
        return coursesRepository.sortCourses(sortType)
    }
}