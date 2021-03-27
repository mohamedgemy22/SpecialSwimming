package com.qersh.swimmig_schole_api.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.qersh.swimmig_schole_api.R
import com.qersh.swimmig_schole_api.model.note
import com.qersh.swimmig_schole_api.recycler_item_details


private  lateinit var onItemClickListener: AdapterView.OnItemClickListener

class note_adapter(private var notelist: List<note>, baseContext: Context) : RecyclerView.Adapter<note_adapter.Notes_Recycler_viewholder> () {

    var filteredList= ArrayList<note>()

    fun setData (notelist: ArrayList<note>){
        this.notelist=notelist
        this.filteredList=notelist
        notifyDataSetChanged()
    }

    ///****


    class Notes_Recycler_viewholder(mycard_item_view: View, var my_current_note: note? = null) :

    /// on item click lister action
        RecyclerView.ViewHolder(mycard_item_view) {
        init {
            mycard_item_view.setOnClickListener {
                Toast.makeText(
                    mycard_item_view.context,
                    my_current_note!!.coach_name,
                    Toast.LENGTH_LONG
                ).show()
                var myintent = Intent(mycard_item_view.context, recycler_item_details::class.java)
                myintent.putExtra("coach_name", my_current_note!!.coach_name)
                myintent.putExtra("coach_location", my_current_note!!.coach_location)
                myintent.putExtra("coach_price", my_current_note!!.coach_price)
                myintent.putExtra("coach_phone", my_current_note!!.coach_phone)
                myintent.putExtra("coach_experience", my_current_note!!.coach_experience)
                myintent.putExtra("url", my_current_note!!.coach_photo.url)


                mycard_item_view.context.startActivity(myintent)


            }
        }

        val Image_item_carddd: ImageView = mycard_item_view.findViewById(R.id.image_itemcard)
        val txt1_item_carddd: TextView = mycard_item_view.findViewById(R.id.text1_itemcard)
        val txt2_item_carddd: TextView = mycard_item_view.findViewById(R.id.text2_itemcard)
        val txt3_item_carddd: TextView = mycard_item_view.findViewById(R.id.text3_itemcard)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Notes_Recycler_viewholder {
        val card_item_view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_card_item, parent, false)
        return Notes_Recycler_viewholder((card_item_view))
    }


    override fun onBindViewHolder(holder: Notes_Recycler_viewholder, position: Int) {
        val currentNote = notelist[position]


        Glide.with(holder.Image_item_carddd).load(currentNote.coach_photo.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .centerCrop()
            .into(holder.Image_item_carddd);


        holder.txt1_item_carddd.setText(currentNote.coach_name)
        holder.txt2_item_carddd.setText(currentNote.coach_location)
        holder.txt3_item_carddd.setText(currentNote.coach_price.toString())
        holder.my_current_note = currentNote  // reach my current note
        //recycler  animation
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.context, (R.anim.rec_anim_wave2)));
    }

    override fun getItemCount(): Int {
        return notelist.size

    }

 }




