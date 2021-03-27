package com.qersh.swimmig_schole_api

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.qersh.swimmig_schole_api.dp.NoteDatabase
import com.qersh.swimmig_schole_api.fragments.fragment_favorite
import com.qersh.swimmig_schole_api.mainViewModel.NoteViewModel
import com.qersh.swimmig_schole_api.mainViewModel.NoteViewModelFactory
import com.qersh.swimmig_schole_api.model.coach_photo
import com.qersh.swimmig_schole_api.model.note
import com.qersh.swimmig_schole_api.repositories.NoteRepository
import kotlinx.android.synthetic.main.activity_recycler_item_details.*


class recycler_item_details : AppCompatActivity() {


    lateinit var mytext1_view: TextView
    lateinit var mytext2_view: TextView
    lateinit var mytext3_view: TextView
    lateinit var mytext4_view: TextView
    lateinit var mytext5_view: TextView
    lateinit var myimage1_view: ImageView
    lateinit var favorit_Image: ImageView
    // lateinit var  myshow_url: TextView

    private lateinit var viewModel: NoteViewModel
    private lateinit var modelFactory: NoteViewModelFactory

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_item_details)

        modelFactory =
            NoteViewModelFactory(NoteRepository(NoteDatabase.getDatabase(this).noteDoa()))
        viewModel = ViewModelProvider(this, modelFactory).get(NoteViewModel::class.java)

        mytext1_view = findViewById(R.id.text1_view)
        mytext2_view = findViewById(R.id.text2_view)
        mytext3_view = findViewById(R.id.text3_view)
        mytext4_view = findViewById(R.id.text4_view)
        mytext5_view = findViewById(R.id.text5_view)
        myimage1_view = findViewById(R.id.image1_view)
        favorit_Image = findViewById(R.id.favorite_button)

        // myshow_url = findViewById(R.id.show_url)

        val my_ccoach_name = intent.extras?.getString("coach_name")
        val my_ccoach_location = intent.extras?.getString("coach_location")
        val my_ccoach_price = intent.extras?.getInt("coach_price")
        val my_ccoach_phone = intent.extras?.getInt("coach_phone")
        val my_ccoach_experience = intent.extras?.getString("coach_experience")
        val my_ccoach_image = intent.extras?.getString("url")


        // i woreked here
        val note = note(
            coach_name = my_ccoach_name ?: " ",
            coach_location = my_ccoach_location ?: " ",
            coach_price = my_ccoach_price ?: 0,
            coach_phone = my_ccoach_phone ?: 0,
            coach_experience = my_ccoach_experience ?: "",
            coach_photo = coach_photo(my_ccoach_image ?: "")
        )
        viewModel.isExixst(my_ccoach_name ?: "")
        favorit_Image.setOnClickListener {
            if (viewModel.bool) {
                println("GEMY delete note")
                viewModel.delete(note.coach_name)
            } else {
                println("GEMY insert note")
                viewModel.insert(note)
            }
            Toast.makeText(this,"تم الاضافة الي المفضلة",Toast.LENGTH_LONG).show()
        }

        // making call phone
        call_button.setOnClickListener {


            val i = Intent(Intent.ACTION_CALL)
            i.data = Uri.parse("tel:$my_ccoach_price")

            val call = Intent(Intent.ACTION_CALL)
            call.data = Uri.parse("tel:$my_ccoach_price")
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startActivity(call)
            } else {
                requestPermissions(arrayOf(CALL_PHONE), 1)
            }
        }

//        var x: String = "/uploads/download_0a89621588.jpg"
//        //removechar()
//        val xyz=x.replace("/uploads","")
//        //myshow_url.text = xyz
//        Toast.makeText(this, xyz, Toast.LENGTH_LONG).show()

        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(myimage1_view);
        //val x:String="/uploads/download_0a89621588.jpg"
        // val o:String="https://cdn1.buyacar.co.uk/sites/buyacar/files/styles/w860/public/ferrari_488_pista_ftq.jpg?itok=mX8-I7MD"

        Glide.with(this).load(my_ccoach_image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .centerCrop()
            .into(myimage1_view);


        mytext1_view.text = my_ccoach_name.toString()
        mytext2_view.text = my_ccoach_location.toString()
        mytext3_view.text = my_ccoach_price.toString()
        mytext4_view.text = my_ccoach_phone.toString()
        mytext5_view.text = my_ccoach_experience.toString()
        // myimage1_view.setImageResource(myimage as Int)


//      Picasso.with(this)
//            .load("https://carsguide-res.cloudinary.com/image/upload/f_auto,fl_lossy,q_auto,t_cg_hero_large/v1/editorial/story/hero_image/2019-Porsche-911-coupe-red-press-image-1001x565p-%281%29.jpg")
//            .fit()
//            .centerCrop()
//            .into(myimage1_view);
        Toast.makeText(this, "تم تحميل الصورة", Toast.LENGTH_LONG).show()
        supportActionBar?.title = my_ccoach_name.toString()
    }
}
