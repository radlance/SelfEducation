package com.example.selfeducation.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.selfeducation.R
import com.example.selfeducation.domain.entity.Course
import com.example.selfeducation.domain.usecase.AddCourseUseCase
import com.example.selfeducation.domain.usecase.EditCourseUseCase
import com.example.selfeducation.domain.usecase.GetCourseListUseCase
import com.example.selfeducation.domain.usecase.SearchCoursesUseCase
import com.example.selfeducation.domain.usecase.SortCoursesUseCase

class CourseCatalogViewModel(
    getCourseListUseCase: GetCourseListUseCase,
    private val editCourseUseCase: EditCourseUseCase,
    private val searchCoursesUseCase: SearchCoursesUseCase,
    private val sortCoursesUseCase: SortCoursesUseCase,
    private val addCourseUseCase: AddCourseUseCase
) : ViewModel() {

    var courseList = getCourseListUseCase.invoke()
    private var _filteredCourse = MutableLiveData<List<Course>>()
    val filteredCourse: LiveData<List<Course>>
        get() =_filteredCourse

    suspend fun initialAdd() {
        for (i in 1..50) {
            addCourseUseCase.invoke(
                Course(
                    "very long title about this course$i",
                    "very long description about this course$i",
                    R.drawable.ic_smile,
                    100,
                    4.0,
                    500,
                    20,
                    isPaid = false,
                    isStarted = false,
                    "author $i"
                )
            )
        }
    }

    fun searchCourses(query: String) {
        _filteredCourse.postValue(searchCoursesUseCase.invoke(query))
    }

    suspend fun changeCourseStatus(course: Course) {
        val startedCourse = course.copy(isStarted = true)
        editCourseUseCase.invoke(startedCourse)
    }
}