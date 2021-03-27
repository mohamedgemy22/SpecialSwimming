package com.qersh.swimmig_schole_api.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.qersh.swimmig_schole_api.R
import com.qersh.swimmig_schole_api.adapters.note_adapter
import com.qersh.swimmig_schole_api.adapters.user_adapter
import com.qersh.swimmig_schole_api.model.coach_photo
import com.qersh.swimmig_schole_api.model.note
import com.qersh.swimmig_schole_api.model.user
import com.tuyenmonkey.mkloader.MKLoader
import org.json.JSONArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_contact.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_contact : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
    ): View? {
        // Inflate the layout for this fragment

        val view =inflater.inflate(R.layout.fragment_coach, container, false)

        val swipeRefreshLayout=view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeRefreshLayout.setOnRefreshListener {

            swipeRefreshLayout.isRefreshing=false

        }
        /////********

        val user_list=ArrayList<user>()
        AndroidNetworking.get("https://swim-coach-api.herokuapp.com/players")
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    // do anything with response
                    for (i in 0 until response.length()) {
                        val all = response.getJSONObject(i)
                        val player_name = all.getString("player_name")
                        val player_password = all.getInt("player_password")
                        val player_email = all.getString("player_email")
                        val player_mobile = all.getInt("player_mobile")

                        user_list.add(user(player_name, player_password, player_email, player_mobile))


                        val fr2_recycler_view: RecyclerView = view.findViewById(R.id.fr2_recycler_view)
                        fr2_recycler_view.adapter = user_adapter(user_list)
                        fr2_recycler_view.layoutManager = LinearLayoutManager(context)
                        fr2_recycler_view.setHasFixedSize(true)


                        // hide loading view
                        val loadingview=view.findViewById<MKLoader>(R.id.loading_view)
                        loadingview.visibility= View.GONE

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


}
