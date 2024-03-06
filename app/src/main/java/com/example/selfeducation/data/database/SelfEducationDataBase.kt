package com.example.selfeducation.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.selfeducation.domain.entity.Course

@Database(entities = [Course::class], version = 1)
abstract class SelfEducationDataBase : RoomDatabase() {
    abstract fun getSelfEducationDao(): SelfEducationDao
    companion object {
        private var INSTANCE: SelfEducationDataBase? = null
        fun getDatabaseInstance(context: Context): SelfEducationDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SelfEducationDataBase::class.java,
                    "self_education_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}