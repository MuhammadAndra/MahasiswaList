package com.example.mahasiswalist.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    object ApiConfig {
        @JvmStatic
        val apiService: ApiService
            get() {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://mahasiswa-api.vercel.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit.create(ApiService::class.java)
            }
    }
}