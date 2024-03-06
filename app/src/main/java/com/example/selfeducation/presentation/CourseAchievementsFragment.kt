package com.example.selfeducation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.selfeducation.databinding.FragmentAchievementsBinding

class CourseAchievementsFragment : Fragment() {
    private var _binding: FragmentAchievementsBinding? = null
    private val binding: FragmentAchievementsBinding
        get() = _binding ?: throw RuntimeException("FragmentCourseCatalogBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAchievementsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): CourseAchievementsFragment {
            return CourseAchievementsFragment()
        }
    }
}