package com.example.planetaria

import MainViewModel
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
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
import java.util.Calendar
import java.util.TimeZone

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        createNotificationChannel()
        val intent = Intent(this@MainActivity, Reminder::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(this@MainActivity, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta")).apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

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

    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "apodChannel"
            val description = "Channel for Apod"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel: NotificationChannel = NotificationChannel("apod", name, importance)
            channel.description = description

            val notificationManager:
                    NotificationManager = getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }
    }
}