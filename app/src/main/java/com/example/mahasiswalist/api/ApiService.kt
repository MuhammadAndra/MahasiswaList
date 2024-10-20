package com.example.mahasiswalist.api

import com.example.mahasiswalist.model.AddMahasiswaResponse
import com.example.mahasiswalist.model.MahasiswaResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    //    @GET("mahasiswa")
//fun getMahasiswa(@Query("nrp") nrp: String?): Call<MahasiswaResponse?>?
    @GET("mahasiswa")
    suspend fun getMahasiswa(@Query("nrp") nrp: String?): Response<MahasiswaResponse>

    //    @POST("mahasiswa")
//    @FormUrlEncoded
//    fun addMahasiswa(
//        @Field("nrp") nrp: String?,
//        @Field("nama") nama: String?,
//        @Field("email") email: String?,
//        @Field("jurusan") jurusan: String?
//    ): Call<AddMahasiswaResponse?>?
    @POST("mahasiswa")
    @FormUrlEncoded
    suspend fun addMahasiswa(
        @Field("nrp") nrp: String?,
        @Field("nama") nama: String?,
        @Field("email") email: String?,
        @Field("jurusan") jurusan: String?
    ): Response<AddMahasiswaResponse>

    //    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "mahasiswa", hasBody = true)
//    fun delMahasiswa(
//        @Field("id") id: String?
//    ): Call<AddMahasiswaResponse?>?
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "mahasiswa", hasBody = true)
    suspend fun delMahasiswa(
        @Field("id") id: String?
    ): Response<AddMahasiswaResponse>

}