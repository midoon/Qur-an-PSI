package com.example.al_quran_kel_1.ayat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.al_quran_kel_1.R

class AyatAdapter(private val listAyat: ArrayList<AyatResponse>): RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {
    inner class AyatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_ayat, parent, false)
        return AyatViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvNomor).text = listAyat[position].nomor
            findViewById<TextView>(R.id.tvAr).text = listAyat[position].ar
            findViewById<TextView>(R.id.tvId).text = listAyat[position].id
        }
    }

    override fun getItemCount(): Int {
        return listAyat.size
    }
}