package com.balivo.navigationdrawerfr

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.balivo.navigationdrawerfr.fragments.HomeFragment
import com.balivo.navigationdrawerfr.fragments.ImagesFragment
import com.balivo.navigationdrawerfr.fragments.ToolsFragment
import com.balivo.navigationdrawerfr.fragments.VideosFragment
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
fragmentTransaction(HomeFragment())

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

        loadFragment(id)
/*
        Space
        for
        Toast
        function
        that
        I am
        not using!
         */

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    // This method will load fragment based on
// the menu id
    private fun loadFragment(menuId: Int) {
        var fragment: Fragment = HomeFragment()

        when (menuId) {
            R.id.nav_home -> fragment = HomeFragment()
            R.id.nav_images -> fragment = ImagesFragment()
            R.id.nav_videos -> fragment = VideosFragment()
            R.id.nav_tools -> fragment = ToolsFragment()
            R.id.nav_about_us -> showAboutUsDialog()
            R.id.nav_feedback -> showFeedBackDialog()
        }

// Fragment Transaction
        fragmentTransaction(fragment)
    }

    // This method will take a fragment and add/replace
// that fragment to the activity
    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    private fun showAboutUsDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("About Us")
        builder.setMessage(("Android was started in order to help developers" +
                " in their android development journey."))
        builder.setCancelable(true)

        builder.setPositiveButton("Ok") { dialog, id -> dialog.cancel() }

        builder.create().show()
    }

    private fun showFeedBackDialog() {
        val builder1 = AlertDialog.Builder(this)

        builder1.setTitle("Feedback")
        builder1.setMessage("Thank you for taking your time to send feed back")
        builder1.setCancelable(true)

// Edittext for message
        val input = EditText(this)
        input.hint = "Message"
        input.height = 200
        input.width = 340
        input.gravity = Gravity.LEFT

        input.imeOptions = EditorInfo.IME_ACTION_DONE
        builder1.setView(input)

        builder1.setPositiveButton("Send") { dialog, i1 ->

            // code to send the message as email
            val i = Intent(Intent.ACTION_SEND)
            i.type = "message/rfc822"
            i.putExtra(Intent.EXTRA_EMAIL, arrayOf("balivo@yahoo.com"))
            i.putExtra(Intent.EXTRA_SUBJECT, "FeedBack")
            i.putExtra(Intent.EXTRA_TEXT, input.text)
            try {
                startActivity(Intent.createChooser(i, "Send mail..."))
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(this@MainActivity,
                        "There are no email clients installed.", Toast.LENGTH_SHORT).show()
            }
            dialog.cancel()
        }
        builder1.create().show()
    }
}