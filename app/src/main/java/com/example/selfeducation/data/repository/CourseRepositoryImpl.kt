package com.example.selfeducation.data.repository

import androidx.lifecycle.LiveData
import com.example.selfeducation.data.database.SelfEducationDao
import com.example.selfeducation.domain.entity.Course
import com.example.selfeducation.domain.entity.SortType
import com.example.selfeducation.domain.repository.CoursesRepository

class CourseRepositoryImpl(private val selfEduCationDao: SelfEducationDao) : CoursesRepository {
    override suspend fun addCourse(course: Course) {
        selfEduCationDao.insertCourse(course)
    }

    override fun getCourseById(courseId: Int): LiveData<Course> {
        return selfEduCationDao.getCourseById(courseId)
    }

    override fun getCourseList(): LiveData<List<Course>> {
        return selfEduCationDao.getCourseList()
    }

    override fun searchCourses(query: String): List<Course> {
        return selfEduCationDao.searchCourses(query)
    }



    override fun sortCourses(sortType: SortType): LiveData<List<Course>> {
        return when(sortType) {
            SortType.PRICE_DESC -> selfEduCationDao.getCoursesByPriceDesc()
            SortType.PRICE_ASC -> selfEduCationDao.getCoursesByPriceAsc()
            SortType.TITLE_ASC -> selfEduCationDao.getCoursesByTitleAsc()
            SortType.RATING_DESC -> selfEduCationDao.getCoursesByRatingDesc()
            SortType.RATING_ASC -> selfEduCationDao.getCoursesByRatingAsc()
            SortType.PEOPLE_COUNT_DESC -> selfEduCationDao.getCoursesByPeopleCountDesc()
            SortType.PEOPLE_COUNT_ASC -> selfEduCationDao.getCoursesByPeopleCountAsc()
        }
    }

    override suspend fun editCourse(course: Course) {
        selfEduCationDao.editCourse(course)
    }
}