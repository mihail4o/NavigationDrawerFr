package com.balivo.navigationdrawerfr

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

// Set-up the Toolbar
        setSupportActionBar(toolbar)

/*
This method is called to toggle the Navigation Drawer whenever
the Hamburger icon is clicked in the ActionBar/Toolbar
*/
        val toggle = ActionBarDrawerToggle(
                this, /* host activity */
                drawer_layout, /* Drawer Layout object */
                toolbar, /* Toolbar Object */
                R.string.nav_open, /* description for accessibility */
                R.string.nav_closed  /* description for accessibility */
        )

// This will load HomeFragment on activity Startup
//fragmentTransaction(HomeFragment())
// Set the drawer toggle as the DrawerListener
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

// Set-up the NavigationView and its listener
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // Called when an item in the navigation menu is selected.
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

// Handle navigation view item clicks here.
        val id = item.itemId
        when (id) {
            R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            R.id.nav_images -> Toast.makeText(this, "Images", Toast.LENGTH_SHORT).show()
            R.id.nav_videos -> Toast.makeText(this, "Videos", Toast.LENGTH_SHORT).show()
            R.id.nav_tools -> Toast.makeText(this, "Tools", Toast.LENGTH_SHORT).show()
            R.id.nav_about_us -> Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
            R.id.nav_feedback -> Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
