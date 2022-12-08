package com.example.al_quran_kel_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnSuratClickListener {

    private val list = ArrayList<DataResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvData = findViewById<RecyclerView>(R.id.rvData)
        rvData.setHasFixedSize(true)
        rvData.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getDatas().enqueue(object: Callback<ArrayList<DataResponse>>{
            override fun onResponse(
                call: Call<ArrayList<DataResponse>>,
                response: Response<ArrayList<DataResponse>>
            ) {
                val responseCode = response.code().toString()
                 response.body()?.let { list.addAll(it) }
                val adapter = DataAdapter(list, this@MainActivity)
                rvData.adapter= adapter

            }

            override fun onFailure(call: Call<ArrayList<DataResponse>>, t: Throwable) {

            }

        })


    }

    override fun onSuratItemClicked(position: Int) {
//        Toast.makeText(this,"klik ${list[position].nomor}",Toast.LENGTH_SHORT).show()
        val  intent = Intent(this,AyatActivity::class.java)
        intent.putExtra("nomor",list[position].nomor)
        intent.putExtra("audio",list[position].audio)
        intent.putExtra("asma",list[position].asma)
        intent.putExtra("arti",list[position].arti)
        startActivity(intent)
    }
}