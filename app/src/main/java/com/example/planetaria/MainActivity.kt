package com.example.planetaria

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.planetaria.apod.ApodFragment
import com.example.planetaria.databinding.ActivityMainBinding
import com.example.planetaria.epic.EpicFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMain)

        binding.bottomNavigationView.setOnItemSelectedListener(this)

        fragmentTransaction(ApodFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
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
            R.id.item_3 -> fragmentTransaction(ItemThreeFragment())
        }
        return true
    }
}