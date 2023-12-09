package com.example.planetaria

import MainViewModel
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.planetaria.apod.ApodFragment
import com.example.planetaria.databinding.ActivityMainBinding
import com.example.planetaria.epic.EpicFragment
import com.example.planetaria.mars.MarsFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application))[MainViewModel::class.java]

        setSupportActionBar(binding.toolbarMain)

        binding.bottomNavigationView.setOnItemSelectedListener(this)

        if (savedInstanceState == null) {
            fragmentTransaction(ApodFragment())
        }

        setDarkMode(viewModel.isDarkMode)
    }

    private fun setDarkMode(darkMode: Boolean) {
        if (darkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            menu?.findItem(R.id.menu)?.setIcon(R.drawable.sun)
        }
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
            menu?.findItem(R.id.menu)?.setIcon(R.drawable.moon)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu -> {
                viewModel.isDarkMode = !viewModel.isDarkMode
                setDarkMode(viewModel.isDarkMode)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun fragmentTransaction(f: Fragment){
        val fragmentManager = supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_container, f)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.apod_menu -> fragmentTransaction(ApodFragment())
            R.id.epic_menu -> fragmentTransaction(EpicFragment())
            R.id.mars_menu -> fragmentTransaction(MarsFragment())
        }
        return true
    }
}