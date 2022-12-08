package com.example.al_quran_kel_1

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.al_quran_kel_1.ayat.AyatAdapter
import com.example.al_quran_kel_1.ayat.AyatResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AyatActivity : AppCompatActivity() {

    private val listAyat = ArrayList<AyatResponse>()

    private lateinit var btnPlay : Button
    private lateinit var btnPause : Button
    var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat)

        val nomor = intent.getStringExtra("nomor")
        val asma = intent.getStringExtra("asma")
        val audio = intent.getStringExtra("audio")
        val arti = intent.getStringExtra("arti")



        findViewById<TextView>(R.id.tvJudul).text = asma
        findViewById<TextView>(R.id.tvJudulArti).text = arti

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

        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)

        btnPlay.setOnClickListener{
            if (audio != null) {
                playAudio(audio)
            }
        }

        btnPause.setOnClickListener{
            pauseAudio()
        }


    }

    private fun pauseAudio() {
        if(mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()

        }else{
            Toast.makeText(this,"Audio has not Played",Toast.LENGTH_SHORT).show()
        }
    }

    private fun playAudio(url: String) {
        val audioUrl = url
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer!!.setDataSource(audioUrl)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException){
            e.printStackTrace()
        }

        Toast.makeText(this,"Audio Started Playing",Toast.LENGTH_SHORT).show()
    }
}