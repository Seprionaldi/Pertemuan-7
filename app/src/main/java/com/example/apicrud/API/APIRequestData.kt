package com.example.apicrud.API

import com.example.apicrud.Models.ModelPetani
import retrofit2.Call
import retrofit2.http.GET

interface APIRequestData {
    // Contoh endpoint untuk mengambil daftar petani
    @GET("/petani")
    fun getDaftarPetani(): Call<List<ModelPetani>> // Gantilah dengan model yang sesuai
    // dan endpoint Anda
}
