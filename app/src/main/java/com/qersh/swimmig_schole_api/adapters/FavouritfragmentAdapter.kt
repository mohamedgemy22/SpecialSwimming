package com.qersh.swimmig_schole_api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.qersh.swimmig_schole_api.R
import com.qersh.swimmig_schole_api.model.note

class FavouritfragmentAdapter(private val notelist: List<note>) :
    RecyclerView.Adapter<FavouritfragmentAdapter.FavouritFragemtViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritFragemtViewHolder {
        val card_item_view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_recycler_card_item, parent, false)
        return FavouritFragemtViewHolder(card_item_view)
    }

    override fun onBindViewHolder(holder: FavouritFragemtViewHolder, position: Int) {
        val currentNote = notelist[position]
        holder.onBind(currentNote)
    }

    override fun getItemCount(): Int = notelist.size


    inner class FavouritFragemtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Image_item_carddd: ImageView = itemView.findViewById(R.id.image_itemcard)
        val txt1_item_carddd: TextView = itemView.findViewById(R.id.text1_itemcard)
        val txt2_item_carddd: TextView = itemView.findViewById(R.id.text2_itemcard)
        val txt3_item_carddd: TextView = itemView.findViewById(R.id.text3_itemcard)

        init {

        }

        fun onBind(currentNote: note) {
            Glide.with(Image_item_carddd).load(currentNote.coach_photo.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .centerCrop()
                .into(Image_item_carddd);


            txt1_item_carddd.setText(currentNote.coach_name)
            txt2_item_carddd.setText(currentNote.coach_location)
            txt3_item_carddd.setText(currentNote.coach_price.toString())
//            my_current_note=currentNote  // reach my current note
        }
    }
}