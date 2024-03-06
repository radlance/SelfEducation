package com.example.selfeducation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnAttachStateChangeListener
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query
import com.example.selfeducation.databinding.FragmentCourseCatalogBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseCatalogFragment : Fragment() {
    private lateinit var vm: CourseCatalogViewModel
    private lateinit var courseListAdapter: CourseListAdapter
    private lateinit var rvCourseList: RecyclerView

    private var _binding: FragmentCourseCatalogBinding? = null
    private val binding: FragmentCourseCatalogBinding
        get() = _binding ?: throw RuntimeException("FragmentCourseCatalogBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        vm = ViewModelProvider(
            this,
            CourseCatalogViewModelFactory(view.context)
        )[CourseCatalogViewModel::class.java]

        setupObserver()
        setupSearchView()
        setupButtonClickListener()
    }

    private fun setupObserver() {
        vm.courseList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                CoroutineScope(Dispatchers.IO).launch { vm.initialAdd() }
            }
            courseListAdapter.courseList = it
        }
    }

    private fun setupRecyclerView() {
        courseListAdapter = CourseListAdapter()
        rvCourseList = binding.rvCourseList

        with(rvCourseList) {
            adapter = courseListAdapter
        }
    }

    private fun setupButtonClickListener() {
        courseListAdapter.onStartButtonClickListener = {
            CoroutineScope(Dispatchers.IO).launch { vm.changeCourseStatus(it) }
        }
    }

    private fun setupSearchView() {
        vm.filteredCourse.observe(viewLifecycleOwner) {
            courseListAdapter.courseList = it
        }
        val searchView = binding.svCourses
        searchView.setOnQueryTextListener(object : OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    CoroutineScope(Dispatchers.IO).launch { vm.searchCourses("%$newText%") }
                } else {
                    setupObserver()
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): CourseCatalogFragment {
            return CourseCatalogFragment()
        }
    }
}