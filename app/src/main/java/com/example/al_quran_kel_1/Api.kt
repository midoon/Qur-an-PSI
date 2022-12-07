package com.example.al_quran_kel_1

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("data")
    fun getDatas(): Call<ArrayList<DataResponse>>
}