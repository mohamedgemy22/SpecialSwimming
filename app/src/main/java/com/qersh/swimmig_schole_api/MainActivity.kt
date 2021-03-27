package com.qersh.swimmig_schole_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.qersh.swimmig_schole_api.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //////***************** Bottom navigation code
        val homefragment=fragment_home()
        val favoritefragment=fragment_favorite()
        val coachfragement=fragment_coach()
        val profilefragement=fragment_profile()
        val contaactusfragement=fragment_contact()



        loadFragment(homefragment)

        val buttom_nav=findViewById<BottomNavigationView>(R.id.buttom_nav)
        buttom_nav.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home_nav->loadFragment(homefragment)
                R.id.favorite_nav->loadFragment(favoritefragment)
                R.id.coach_nav->loadFragment(coachfragement)
                R.id.profile_nav->loadFragment(profilefragement)
                R.id.contactus_nav->loadFragment(contaactusfragement)
            }
            true
        }


    }
    private  fun loadFragment (fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout,fragment)
            commit()



        //////***************** drawer navigation code

        setSupportActionBar(findViewById(R.id.toolbar))

        navigation_view.setNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, fragment_home()).commit()
                    drawer.closeDrawer(GravityCompat.START)

                    //navigation_view?.visibility = android.view.View.GONE
//                    drawer?.setDrawerLockMode(frame_layout.LOCK_MODE_UNLOCKED) //To unlock navigation drawer
                    true
                }

                R.id.nav_gallery -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, fragment_profile()).commit()
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_slideshow -> {
                    val add_coach_shift: Intent = Intent(applicationContext,search_coach_activity::class.java)
                    startActivity(add_coach_shift)
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_tools -> {
                    val add_coach_shift: Intent = Intent(applicationContext,add_coach::class.java)
                    startActivity(add_coach_shift)
                    drawer.closeDrawer(GravityCompat.START)
                    true

                }
                R.id.nav_share -> {

                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, "Hey Check out this Great app:")
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Share To:"))

                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_send -> {

                    drawer.closeDrawer(GravityCompat.START)
                    true
                }


                else -> {
                    false
                }
            }
        }

        val drawerToggle = ActionBarDrawerToggle(this@MainActivity, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        ///***************


    }

}