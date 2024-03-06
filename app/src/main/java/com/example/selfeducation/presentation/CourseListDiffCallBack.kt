package com.example.selfeducation.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.selfeducation.domain.entity.Course

class CourseListDiffCallBack(
    private val oldList: List<Course>,
    private val newList: List<Course>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}