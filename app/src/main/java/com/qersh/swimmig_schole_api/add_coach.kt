package com.qersh.swimmig_schole_api

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import com.qersh.swimmig_schole_api.model.coach_photo
import com.qersh.swimmig_schole_api.model.note
import kotlinx.android.synthetic.main.activity_add_coach.*
import kotlinx.android.synthetic.main.activity_user_details.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class add_coach : AppCompatActivity() {

    private val pickImage = 100
    private var imageUri: Uri? = null
    val urlpath =imageUri.toString()
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coach)

        LoadPicture_but.setOnClickListener {

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
}
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK && requestCode == pickImage) {
        imageUri = data?.data
        imageView.setImageURI(imageUri).toString()

    }


        val but11_txt = findViewById<Button>(R.id.but1_txt)
        but11_txt.setOnClickListener {

            val retrofit_Builder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://srcs-swimming.herokuapp.com/")
                    .build()


            val card_brand_edttxt = findViewById(R.id.card_brand_edttxt) as EditText
            val card_model_edttxt = findViewById(R.id.card_model_edttxt) as EditText
            val coach_price_edttxt = findViewById(R.id.coach_price_edttxt) as EditText
            val coach_phone_edttxt = findViewById(R.id.coach_phone_edttxt) as EditText
            val coach_experience_edttxt = findViewById(R.id.coach_experience_edttxt) as EditText
            val imageView = findViewById(R.id.imageView) as ImageView

            ////***************
            val error_txt = findViewById(R.id.error_txt) as TextView


            val my_note = note(card_brand_edttxt.text.toString(), card_model_edttxt.text.toString(), coach_price_edttxt.text.toString().toInt(),coach_phone_edttxt.text.toString().toInt(),coach_experience_edttxt.text.toString(),coach_photo(imageUri.toString()))

            // val my_note=note("محمد القرش","القليوبية",150,(coach_image("https://res.cloudinary.com/elkersh-apps/image/upload/v1610579231/31018402_919e0b27c3.jpg")))
            //*************
            ///************

            val jsonPlaceHolderApi = retrofit_Builder.create(JsonPlaceHolderApi::class.java)
            val call = jsonPlaceHolderApi.sendurldata(my_note)

            call.enqueue(object : Callback<note> {
                override fun onResponse(call: Call<note>, response: Response<note>) {
                    response.code()
                    Toast.makeText(applicationContext, "تم تحميل البيانات", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<note>, t: Throwable) {
                    error_txt.text = t.message.toString()
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()


                }

            })

            //Toast.makeText(view.context, "زرار ياقرش جو  الفراجمنت", Toast.LENGTH_LONG).show()

        }
        //************




        //*************

        // Inflate the layout for this fragment
        return


//    }

//    private fun setImage(uri: Uri) {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//            val source = ImageDecoder.createSource(contentResolver, imageUri!!)
//            val bitmap = ImageDecoder.decodeBitmap(source)
//            imageView.setImageBitmap(bitmap)
//
//        } else {
//            @Suppress("DEPRECATION") val bitmap =
//                    MediaStore.Images.Media.getBitmap(contentResolver, uri)
//            imageView.setImageBitmap(bitmap)
//
//        }
    }
}