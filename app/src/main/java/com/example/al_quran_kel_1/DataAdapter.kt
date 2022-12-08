package com.example.al_quran_kel_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val list: ArrayList<DataResponse>, private val onSuratClickListener: OnSuratClickListener): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {



    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        fun bind(dataResponse: DataResponse){
//            with(itemView){
//                findViewById<TextView>(R.id.tvAsma).text = dataResponse.asma
//                findViewById<TextView>(R.id.tvArti).text = dataResponse.arti
//                findViewById<TextView>(R.id.tvAyat).text = dataResponse.ayat.toString() + " Ayat"
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvAsma).text = list[position].asma
            findViewById<TextView>(R.id.tvArti).text = list[position].arti
            findViewById<TextView>(R.id.tvAyat).text = list[position].ayat.toString() + " Ayat"
        }

        holder.itemView.setOnClickListener{
            onSuratClickListener.onSuratItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}