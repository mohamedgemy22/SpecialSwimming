package com.qersh.swimmig_schole_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_user_details.*

class user_details : AppCompatActivity() {


    lateinit var  mytext1_view: TextView
    lateinit var  mytext2_view: TextView
    lateinit var  mytext3_view: TextView
    lateinit var  mytext4_view: TextView
    lateinit var  myimage1_view: ImageView
    lateinit var  myshow_url: TextView


    var slider_images:IntArray = intArrayOf(
        R.drawable.slider_11,
        R.drawable.slider_22,
        R.drawable.slider_33,
        R.drawable.slider_44

    )

    var poosl:Array<String> = arrayOf(
        "cairo",
        "alex",
        "aswan",
        "giza"



    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        slider_view.pageCount=poosl.size
        slider_view.setImageListener { position, imageView ->
            imageView.setImageResource(slider_images[position])
        }
        slider_view.setImageClickListener { position->
            Toast.makeText(applicationContext,poosl[position],Toast.LENGTH_SHORT).show()
        }


        mytext1_view = findViewById(R.id.text1_view)
        mytext2_view = findViewById(R.id.text2_view)
        mytext3_view = findViewById(R.id.text3_view)
        mytext4_view = findViewById(R.id.text4_view)
        myimage1_view = findViewById(R.id.image1_view)
        myshow_url = findViewById(R.id.show_url)

        val my_pplayer_name = intent.extras?.getString("player_name")
        val my_pplayer_password = intent.extras?.getInt("player_password")
        val my_pplayer_email = intent.extras?.getString("player_email")
        val my_pplayer_mobile = intent.extras?.getInt("player_mobile")

//
//        Glide.with(this).load(my_ccoach_image)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .fitCenter()
//            .centerCrop()
//            .into(myimage1_view);


        mytext1_view.text = my_pplayer_name.toString()
        mytext2_view.text = my_pplayer_password.toString()
        mytext3_view.text = my_pplayer_email.toString()
        mytext4_view.text = my_pplayer_mobile.toString()
        // myimage1_view.setImageResource(myimage as Int)


//      Picasso.with(this)
//            .load("https://carsguide-res.cloudinary.com/image/upload/f_auto,fl_lossy,q_auto,t_cg_hero_large/v1/editorial/story/hero_image/2019-Porsche-911-coupe-red-press-image-1001x565p-%281%29.jpg")
//            .fit()
//            .centerCrop()
//            .into(myimage1_view);
        Toast.makeText(this, "تم تحميل الصورة", Toast.LENGTH_LONG).show()
        supportActionBar?.title = my_pplayer_name.toString()
    }






}
