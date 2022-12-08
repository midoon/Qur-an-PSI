package com.example.al_quran_kel_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.al_quran_kel_1.ayat.AyatAdapter
import com.example.al_quran_kel_1.ayat.AyatResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AyatActivity : AppCompatActivity() {

    private val listAyat = ArrayList<AyatResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat)

        val nomor = intent.getStringExtra("nomor")
        val asma = intent.getStringExtra("asma")
        val audio = intent.getStringExtra("audio")
        val arti = intent.getStringExtra("arti")



        findViewById<TextView>(R.id.tvJudul).text = asma

        val rvAyat = findViewById<RecyclerView>(R.id.rvAyat)
        rvAyat.setHasFixedSize(true)
        rvAyat.layoutManager = LinearLayoutManager(this)

        if (nomor != null) {
            RetrofitClient.instance.getSurat(nomor.toInt()).enqueue(object: Callback<ArrayList<AyatResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<AyatResponse>>,
                    response: Response<ArrayList<AyatResponse>>
                ) {
                    val responseCode = response.code().toString()
                    response.body()?.let { listAyat.addAll(it) }
                    val adapter = AyatAdapter(listAyat)
                    rvAyat.adapter= adapter

                }

                override fun onFailure(call: Call<ArrayList<AyatResponse>>, t: Throwable) {

                }

            })
        }
    }
}