package com.qersh.swimmig_schole_api.fragments

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.inflate
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat.collapseActionView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.qersh.swimmig_schole_api.MainActivity
import com.qersh.swimmig_schole_api.R
import com.qersh.swimmig_schole_api.adapters.note_adapter
import com.qersh.swimmig_schole_api.model.coach_photo
import com.qersh.swimmig_schole_api.model.note
import com.tuyenmonkey.mkloader.MKLoader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search_coach_activity.*
import kotlinx.android.synthetic.main.fragment_coach.*
import kotlinx.android.synthetic.main.fragment_coach.view.*
import org.json.JSONArray


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

val notelist = ArrayList<note>()
class fragment_coach : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_coach, container, false)

        //loading_view.seton
        //***********

        val swipeRefreshLayout=view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeRefreshLayout.setOnRefreshListener {

            swipeRefreshLayout.isRefreshing=false

        }
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

                        val fr2_recycler_view: RecyclerView =
                            view!!.findViewById(R.id.fr2_recycler_view)
                        fr2_recycler_view.adapter = note_adapter(notelist, context!!)
                        fr2_recycler_view.layoutManager = LinearLayoutManager(context)
                        fr2_recycler_view.setHasFixedSize(true)

                        // hide loading view
                        val loadingview=view.findViewById<MKLoader>(R.id.loading_view)
                        loadingview.visibility= GONE


                    }

                }

                override fun onError(error: ANError) {
                    Toast.makeText(context, "خطأ في ال api", Toast.LENGTH_LONG).show()
                    // handle error
                }

            })


        // Inflate the layout for this fragment
        return view
    }

    ///*******************************************************************************
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.mainnn_menu, menu)
        val menuItem = menu.findItem(R.id.search_action)
        val searchView = menuItem.actionView as androidx.appcompat.widget.SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = "اختر مدربك الخاص"
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(filterString: String?): Boolean {

                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                menuItem.collapseActionView()
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtermodelist = filter(notelist, newText!!)
                fr2_recycler_view.adapter =
                    note_adapter(filtermodelist, baseContext = context!!.applicationContext)
                fr2_recycler_view.adapter!!.notifyDataSetChanged()
                return true
            }

        })
        searchView.setOnClickListener { view -> }
        return super.onCreateOptionsMenu(menu, menuInflater)
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
