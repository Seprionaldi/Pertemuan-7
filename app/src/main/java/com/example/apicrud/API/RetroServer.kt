package com.example.apicrud.API

import ApiService


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroServer {
    companion object {
        val BASE_URL = "http://localhost:8080/petani/"

        // ...

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) // Pastikan baseUrl diakhiri dengan "/"
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
