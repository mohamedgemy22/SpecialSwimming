package com.qersh.swimmig_schole_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View.GONE
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.qersh.swimmig_schole_api.fragments.fragment_profile
import kotlinx.android.synthetic.main.activity_splash.*

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo_anim = AnimationUtils.loadAnimation(this, R.anim.anim_but1);
        logo_splash.startAnimation(logo_anim)

        val text_anim = AnimationUtils.loadAnimation(this, R.anim.anim_but2);
        text_splash.startAnimation(text_anim)


        val alpha_anim = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        splash_colored.startAnimation(alpha_anim)
        //splash_grey.visibility=GONE
        val anim_anim3 = AnimationUtils.loadAnimation(this, R.anim.anim_but3);
        splash_colored.startAnimation(anim_anim3)

        val anim_anim4 = AnimationUtils.loadAnimation(this, R.anim.anim_but4);
        logo_splash.startAnimation(anim_anim4)


        Handler().postDelayed({
            startActivity(Intent(this,login::class.java))
            finish()
        }, 6000)
    }

        }