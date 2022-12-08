package com.example.al_quran_kel_1

import com.example.al_quran_kel_1.ayat.AyatResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("data")
    fun getDatas(): Call<ArrayList<DataResponse>>

    @GET("surat/{nomor}")
    fun getSurat(@Path("nomor") nomoId: Int): Call<ArrayList<AyatResponse>>
}