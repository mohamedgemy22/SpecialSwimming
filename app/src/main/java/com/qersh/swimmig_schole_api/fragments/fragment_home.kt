package com.qersh.swimmig_schole_api.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.checkSelfPermission
import com.qersh.swimmig_schole_api.JsonPlaceHolderApi

import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.Manifest
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View.GONE
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.qersh.swimmig_schole_api.R
import com.qersh.swimmig_schole_api.adapters.home_note_adapter
import com.qersh.swimmig_schole_api.adapters.note_adapter
import com.qersh.swimmig_schole_api.model.coach_photo
import com.qersh.swimmig_schole_api.model.note
import com.synnapps.carouselview.CarouselView
import com.tuyenmonkey.mkloader.MKLoader
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.fragment_coach.*
import kotlinx.android.synthetic.main.fragment_home.slider_view
import org.json.JSONArray
import java.io.File
import java.io.FileNotFoundException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    var slider_images:IntArray= intArrayOf(
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
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {


        val view = inflater.inflate(R.layout.fragment_home, container, false)


        //*********

        val my_slider_view = view.findViewById(R.id.slider_view) as CarouselView
        my_slider_view.pageCount=poosl.size
        my_slider_view.setImageListener { position, imageView ->
            imageView.setImageResource(slider_images[position])
        }
        my_slider_view.setImageClickListener { position->
            Toast.makeText(view.context,poosl[position],Toast.LENGTH_SHORT).show()
        }

        //***********
//        val coach_serachview=view.findViewById(R.id.coach_serachview) as SearchView
//        coach_serachview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//
//
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                (fr2_recycler_view.adapter as Filterable).filter.filter(query)
//
//
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//
//                // note_adapter.filter.filter(newText)
//
//                return false
//            }
////            override fun onOptionsItemSelceted(item: MenuItem?):Boolean{
////            val id = item?.itemId
////            return if (id == R.id.coach_serachview){
////                true
////            } else super.onOptionsItemSelected(item)
//
//
//        })
//
//        /////********

        val notelist = ArrayList<note>()
        AndroidNetworking.get("https://srcs-swimming.herokuapp.com/coaches")
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {
                        // do anything with response
                        for (i in 0 until response.length()) {
                            val all = response.getJSONObject(i)
                            val coach_name = all.getString("coach_name")
                            val coach_location = all.getString("coach_location")
                            val coach_price = all.getInt("coach_price")
                            val coach_phone = all.getInt("coach_phone")
                            val coach_experience = all.getString("coach_experience")
                            val coach_photo = all.getJSONObject("coach_photo")
                            val url = coach_photo.getString("url")

                            notelist.add(
                                    note(
                                            coach_name,
                                            coach_location,
                                            coach_price,
                                            coach_phone ,
                                            coach_experience,
                                            coach_photo(url)
                                    )
                            )

                            /// ******* horizontal recycler view ******
                            val fr2_recycler_view: RecyclerView =
                                    view.findViewById(R.id.fr2_recycler_view)
                            fr2_recycler_view.adapter = home_note_adapter(notelist)

                             val layoutManager: RecyclerView.LayoutManager by lazy {
                                LinearLayoutManager(view.context).apply {
                                    orientation = LinearLayoutManager.HORIZONTAL
                                }}
                            fr2_recycler_view.layoutManager = layoutManager
                            fr2_recycler_view.setHasFixedSize(true)


                            // hide loading view
                            val loading_view=view.findViewById<MKLoader>(R.id.loading_view)
                            loading_view.visibility= GONE
                        }

                    }
                    //*******

                    //**********
                    override fun onError(error: ANError) {
                        Toast.makeText(context, "خطأ في ال api", Toast.LENGTH_LONG).show()
                        // handle error
                    }

                })


        // Inflate the layout for this fragment
        return view
    }


}







//*******************************************************
/// holden code fragment image upload
//val but11_txt = view!!.findViewById<Button>(R.id.but1_txt)
//but11_txt.setOnClickListener {
//
//    val retrofit_Builder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://swim-coach-api.herokuapp.com/")
//            .build()
//
//
//    val card_brand_edttxt = view.findViewById(R.id.card_brand_edttxt) as EditText
//    val card_model_edttxt = view.findViewById(R.id.card_model_edttxt) as EditText
//    val coach_price_edttxt = view.findViewById(R.id.coach_price_edttxt) as EditText
//    val car_image_imgview = view.findViewById(R.id.imageView) as ImageView
//
//
//    val error_txt = view.findViewById(R.id.error_txt) as TextView
//
//    val my_note = note(card_brand_edttxt.text.toString(), card_model_edttxt.text.toString(), coach_price_edttxt.text.toString().toInt(), (coach_image(car_image_imgview.resources.toString())))
//
//    // val my_note=note("محمد القرش","القليوبية",150,(coach_image("https://res.cloudinary.com/elkersh-apps/image/upload/v1610579231/31018402_919e0b27c3.jpg")))
//
//
//    //val note= note(card_brand_edttxt.text.toString(),card_model_edttxt.text.toString(),car_image_imgview.resources.toString() )
//    val jsonPlaceHolderApi = retrofit_Builder.create(JsonPlaceHolderApi::class.java)
//    val call = jsonPlaceHolderApi.sendurldata(my_note)
//    call.enqueue(object : Callback<note> {
//        override fun onResponse(call: Call<note>, response: Response<note>) {
//            response.code()
//            Toast.makeText(view.context, "تم تحميل البيانات", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onFailure(call: Call<note>, t: Throwable) {
//            error_txt.text = t.message.toString()
//            Toast.makeText(view.context, t.message.toString(), Toast.LENGTH_LONG).show()
//
//
//        }
//
//    })
//
//    //Toast.makeText(view.context, "زرار ياقرش جو  الفراجمنت", Toast.LENGTH_LONG).show()
//
//}
////************


