package com.example.selfeducation.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course(
    val title: String,
    val description: String,
    val imageUrl: Int,
    val price: Int,
    val rating: Double,
    @ColumnInfo(name = "people_count") val peopleCount: Int,
    val lessonsCount: Int,
    val isPaid: Boolean,
    val isStarted: Boolean,
    val author: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)
