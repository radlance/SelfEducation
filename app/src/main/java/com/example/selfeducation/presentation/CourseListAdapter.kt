package com.example.selfeducation.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.selfeducation.R
import com.example.selfeducation.databinding.ItemCourseDefaultBinding
import com.example.selfeducation.domain.entity.Course

class CourseListAdapter : RecyclerView.Adapter<CourseListAdapter.CourseListViewHolder>() {
    var courseList = listOf<Course>()
        set(value) {
            val callback = CourseListDiffCallBack(courseList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var onCourseClickListener: ((Course) -> Unit)? = null
    var onStartButtonClickListener: ((Course) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_STARTED = 1
        const val VIEW_TYPE_DEFAULT = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DEFAULT -> R.layout.item_course_default
            VIEW_TYPE_STARTED -> R.layout.item_course_started
            else -> throw RuntimeException("unknown viewType $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CourseListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: CourseListViewHolder, position: Int) {
        val course = courseList[position]
        holder.itemView.setOnClickListener {
            onCourseClickListener?.invoke(course)
        }

        holder.button.setOnClickListener {
            onStartButtonClickListener?.invoke(course)
        }

        holder.bind(course)
    }

    override fun getItemViewType(position: Int): Int {
        return if (courseList[position].isStarted) {
            VIEW_TYPE_STARTED
        } else {
            VIEW_TYPE_DEFAULT
        }
    }

    class CourseListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCourseDefaultBinding.bind(itemView)
        val button = binding.buttonCourse
        fun bind(course: Course) {
            with(binding) {
                tvAuthor.text = course.author
                tvTitle.text = course.title
                tvRating.text = course.rating.toString()
                tvCountOfPeople.text = course.peopleCount.toString()
                tvPrice.text = course.price.toString()
                ivCourse.setImageResource(R.drawable.ic_smile)
            }
        }
    }
}