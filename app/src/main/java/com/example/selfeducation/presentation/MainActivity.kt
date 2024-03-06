package com.example.selfeducation.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.selfeducation.R
import com.example.selfeducation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchBottomNavigation()
        replaceFragment(CourseCatalogFragment.newInstance())
    }

    private fun launchBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_achievement -> {
                    replaceFragment(CourseAchievementsFragment.newInstance())
                    true
                }
                R.id.item_catalog -> {
                    replaceFragment(CourseCatalogFragment.newInstance()
                    )
                    true
                }
                else -> throw RuntimeException("Undefined MenuItem: $it")
            }
        }

        binding.bottomNav.setOnItemReselectedListener {  }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainContainer.id, fragment)
            .commit()
    }
}