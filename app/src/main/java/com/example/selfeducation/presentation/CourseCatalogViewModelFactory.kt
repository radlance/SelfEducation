package com.example.selfeducation.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selfeducation.data.database.SelfEducationDataBase
import com.example.selfeducation.data.repository.CourseRepositoryImpl
import com.example.selfeducation.domain.usecase.AddCourseUseCase
import com.example.selfeducation.domain.usecase.EditCourseUseCase
import com.example.selfeducation.domain.usecase.GetCourseListUseCase
import com.example.selfeducation.domain.usecase.SearchCoursesUseCase
import com.example.selfeducation.domain.usecase.SortCoursesUseCase

class CourseCatalogViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val db = SelfEducationDataBase.getDatabaseInstance(context)
    private val selfEducationDao = db.getSelfEducationDao()
    private val courseRepositoryImpl = CourseRepositoryImpl(selfEducationDao)

    private val getCourseListUseCase = GetCourseListUseCase(courseRepositoryImpl)
    private val editCourseUseCase = EditCourseUseCase(courseRepositoryImpl)
    private val searchCoursesUseCase = SearchCoursesUseCase(courseRepositoryImpl)
    private val sortCoursesUseCase = SortCoursesUseCase(courseRepositoryImpl)
    private val addCourseUseCase = AddCourseUseCase(courseRepositoryImpl)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CourseCatalogViewModel(
            getCourseListUseCase,
            editCourseUseCase,
            searchCoursesUseCase,
            sortCoursesUseCase,
            addCourseUseCase
        ) as T
    }
}