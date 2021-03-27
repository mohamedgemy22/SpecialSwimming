package com.qersh.swimmig_schole_api

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.qersh.swimmig_schole_api.adapters.note_adapter
import com.qersh.swimmig_schole_api.model.coach_photo
import com.qersh.swimmig_schole_api.model.note
import com.tuyenmonkey.mkloader.MKLoader

import kotlinx.android.synthetic.main.activity_search_coach_activity.*
import kotlinx.android.synthetic.main.fragment_coach.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import retrofit2.http.Header
import retrofit2.http.Headers
import java.nio.file.attribute.AclEntry.newBuilder

class search_coach_activity : AppCompatActivity() {
    // lateinit var filter_note_adapter : note_adapter
    val notelist = ArrayList<note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_coach_activity)


        //***********

        val swipeRefreshLayout=findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeRefreshLayout.setOnRefreshListener {

            swipeRefreshLayout.isRefreshing=false

        }
        //***********
        setSupportActionBar(toolbar)
        supportActionBar?.title = "ابحث عن مدرب سباحة"

        // /******** اضافة key
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                    .header("Authorization", "MY_API_KEY") // <-- this is the important line

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        //**********
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


                            val search_coach_recycler: RecyclerView =
                                    findViewById(R.id.search_coach_recycler)
                            search_coach_recycler.adapter = note_adapter(notelist, baseContext)
                            search_coach_recycler.layoutManager =
                                    LinearLayoutManager(applicationContext)
                            search_coach_recycler.setHasFixedSize(true)




                            // hide loading view
                            val loadingview=findViewById<MKLoader>(R.id.loading_view)
                            loadingview.visibility= View.GONE


                        }


                    }
                    //*******

                    //**********
                    override fun onError(error: ANError) {
                        Toast.makeText(applicationContext, "خطأ في ال api", Toast.LENGTH_LONG).show()
                        // handle error
                    }

                })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.mainnn_menu, menu)
        val menuItem = menu!!.findItem(R.id.search_action)
        val searchView = menuItem!!.actionView as androidx.appcompat.widget.SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint="اختر مدربك الخاص"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(filterString: String?): Boolean {

                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                menuItem.collapseActionView()
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtermodelist = filter(notelist, newText!!)
                search_coach_recycler.adapter = note_adapter(filtermodelist, baseContext)
                search_coach_recycler.adapter!!.notifyDataSetChanged()
                return true
            }


        })

        return super.onCreateOptionsMenu(menu)
    }

    fun filter(itemList: List<note>, query: String): ArrayList<note> {
        var queryLower = query.toLowerCase()
        val filteredModeList = ArrayList<note>()
        for (model in itemList) {
            val text = model.coach_name.toLowerCase()
            if (text.contains(queryLower)) {
                filteredModeList.add(model)
            }
        }
        for (model in itemList) {
            val text = model.coach_location.toLowerCase()
            if (text.contains(queryLower)) {
                filteredModeList.add(model)
            }
        }
        for (model in itemList) {
            val text = model.coach_price.toString().toLowerCase()
            if (text.contains(queryLower)) {
                filteredModeList.add(model)
            }
        }
        return filteredModeList

    }
}

