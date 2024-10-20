package com.example.mahasiswalist.pages

import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mahasiswalist.api.ApiConfig.ApiConfig.apiService
import com.example.mahasiswalist.model.AddMahasiswaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun InsertUpdatePage(modifier: Modifier = Modifier, navController: NavController) {
    val mContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var nrp by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }
    fun emptyData() {
        nrp = ""
        name = ""
        email = ""
        major = ""
    }

    suspend fun addDataMahasiswa() {
        if (nrp.isEmpty() || name.isEmpty() ||
            email.isEmpty() || major.isEmpty()
        ) {
            withContext(Dispatchers.Main){
                Toast.makeText(
                    mContext, "Silahkan lengkapi form terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            val response = apiService.addMahasiswa(nrp, name, email, major)
            if (response.isSuccessful) {
                val addMahasiswaResponse = response.body()
                if (addMahasiswaResponse != null) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            mContext,
                            "Berhasil menambahakan silahakan cek data pada halaman list!",
                            Toast.LENGTH_SHORT
                        ).show()
                        emptyData()
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(
                            "", "onFailure: " + response.body()!!.message
                        )
                    }
                }
            }
        }
    }

//    fun addDataMahasiswa() {
//        if (nrp.isEmpty() || name.isEmpty() ||
//            email.isEmpty() || major.isEmpty()
//        ) {
//            Toast.makeText(
//                mContext, "Silahkan lengkapi form terlebih dahulu",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            val client = apiService.addMahasiswa(nrp, name, email, major)
//            if (client != null) {
//                client.enqueue(object : Callback<AddMahasiswaResponse?> {
//                    override fun onResponse(
//                        call:
//                        Call<AddMahasiswaResponse?>, response:
//                        Response<AddMahasiswaResponse?>
//                    ) {
//                        if (response.isSuccessful) {
//                            if (response.body() != null) {
//                                Toast.makeText(
//                                    mContext,
//                                    "Berhasil menambahakan silahakan cek data pada halaman list!",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        } else {
//                            if (response.body() != null) {
//                                Log.e(
//                                    "", "onFailure: " + response.body()!!.message
//                                )
//                            }
//                        }
//                    }
//
//                    override fun onFailure(
//                        call:
//                        Call<AddMahasiswaResponse?>, t: Throwable
//                    ) {
//                        Log.e(
//                            "Error Retrofit", "onFailure: " + t.message
//                        )
//                    }
//                })
//            }
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        SimpleTextField(
            value = nrp,
            onValueChange = { nrp = it },
            label = "Masukkan NIM anda"
        )
        SimpleTextField(
            value = name,
            onValueChange = { name = it },
            label = "Masukkan nama anda"
        )
        SimpleTextField(
            value = email,
            onValueChange = { email = it },
            label = "Masukkan email anda"
        )
        SimpleTextField(
            value = major,
            onValueChange = { major = it },
            label = "Masukkan jurusan anda"
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { coroutineScope.launch(Dispatchers.IO){ addDataMahasiswa() } }) {
            Text(text = "Tambah Data")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("searchpage") }) {
            Text(text = "Cari Mahasiswa")
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun InsertUpdatePagePreview() {
    InsertUpdatePage(navController = rememberNavController())
}

@Composable
fun SimpleTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label, color = Color.Gray
                )
            })
        Spacer(modifier = Modifier.height(10.dp))

    }
}