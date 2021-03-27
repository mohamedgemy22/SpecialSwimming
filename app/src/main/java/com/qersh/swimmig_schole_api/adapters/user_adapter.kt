package com.qersh.swimmig_schole_api.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.qersh.swimmig_schole_api.R
import com.qersh.swimmig_schole_api.model.note
import com.qersh.swimmig_schole_api.model.user
import com.qersh.swimmig_schole_api.recycler_item_details
import com.qersh.swimmig_schole_api.user_details


private  lateinit var onItemClickListener: AdapterView.OnItemClickListener

class user_adapter(private val userlist:List<user>) : RecyclerView.Adapter<user_adapter.User_Recycler_viewholder> () {

    ///****


    class User_Recycler_viewholder(mycard_item_view: View, var my_current_user: user?=null) :

    /// on item click lister action
        RecyclerView.ViewHolder(mycard_item_view) {
        init {
            mycard_item_view.setOnClickListener{
                Toast.makeText(mycard_item_view.context, my_current_user!!.player_name, Toast.LENGTH_LONG).show()
                var myintent= Intent(mycard_item_view.context, user_details::class.java)
                myintent.putExtra("player_name",my_current_user!!.player_name)
                myintent.putExtra("player_password",my_current_user!!.player_password)
                myintent.putExtra("player_email",my_current_user!!.player_email)
                myintent.putExtra("player_mobile",my_current_user!!.player_mobile)


                mycard_item_view.context.startActivity(myintent)


            }
        }
      //  val Image_item_carddd: ImageView = mycard_item_view.findViewById(R.id.image_itemcard)
        val txt1_item_carddd: TextView = mycard_item_view.findViewById(R.id.text1_itemcard)
        val txt2_item_carddd: TextView = mycard_item_view.findViewById(R.id.text2_itemcard)
        val txt3_item_carddd: TextView = mycard_item_view.findViewById(R.id.text3_itemcard)
       // val txt4_item_carddd: TextView = mycard_item_view.findViewById(R.id.text4_itemcard)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): user_adapter.User_Recycler_viewholder {
        val card_item_view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_card_item, parent, false)
        return user_adapter.User_Recycler_viewholder((card_item_view))
    }


    override fun onBindViewHolder(holder: user_adapter.User_Recycler_viewholder, position: Int) {
        val currentuser = userlist[position]


//        Glide.with(holder.Image_item_carddd).load(currentNote.coach_image.url)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .fitCenter()
//            .centerCrop()
//            .into(holder.Image_item_carddd);


        holder.txt1_item_carddd.setText(currentuser.player_name)
        holder.txt2_item_carddd.setText(currentuser.player_email)
        holder.txt3_item_carddd.setText(currentuser.player_mobile.toString())
       // holder.txt4_item_carddd.setText(currentuser.player_password.toString())
        holder.my_current_user=currentuser  // reach my current note




    }

    override fun getItemCount(): Int {
        return userlist.size

    }



}


