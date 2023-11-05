package com.example.myownapp

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Profillefragment())
                .commit()
            navigationView.setCheckedItem(R.id.profile)
        }

        val navbar: TextView = findViewById(R.id.navbar)
        navbar.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val v1: CardView = findViewById(R.id.health)
        val v2: CardView = findViewById(R.id.result)
        val v3: CardView = findViewById(R.id.tt)
        val v4: CardView = findViewById(R.id.appoint)
        val v5: CardView = findViewById(R.id.event)

        v1.setOnClickListener {
            startActivity(Intent(this, StudentHealthInfoActivity::class.java))
        }
        v2.setOnClickListener {
            startActivity(Intent(this, GradesActivity::class.java))
        }
        v3.setOnClickListener {
            startActivity(Intent(this, TimetableActivity::class.java))
        }
        v4.setOnClickListener {
            startActivity(Intent(this, AppointmentBookingActivity::class.java))
        }
        v5.setOnClickListener {
            startActivity(Intent(this, EventsActivity::class.java))
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Profillefragment())
                .commit()
            R.id.home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Homefragment())
                .commit()

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
