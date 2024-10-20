package com.example.mahasiswalist.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mahasiswalist.api.ApiConfig
import com.example.mahasiswalist.api.ApiConfig.ApiConfig.apiService
import com.example.mahasiswalist.model.AddMahasiswaResponse
import com.example.mahasiswalist.model.Mahasiswa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SearchPage(modifier: Modifier = Modifier, navController: NavController) {
    val mContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var mahasiswaList: List<Mahasiswa>? = null
    var id by remember { mutableStateOf("") }
    var nrpSearch by remember { mutableStateOf("") }
    var nrp by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }

    fun setData(mahasiswaList: List<Mahasiswa>?) {
        id = mahasiswaList!![0].id.toString()
        nrp = mahasiswaList!![0].nrp.toString()
        name = mahasiswaList[0].nama.toString()
        email = mahasiswaList[0].email.toString()
        major = mahasiswaList[0].jurusan.toString()
    }

    fun emptyData() {
        id = ""
        nrp = ""
        name = ""
        email = ""
        major = ""
    }

    suspend fun deleteMahasiswa() {
        if (id == "") {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    mContext,
                    "Pastikan Mahasiswa yang dihapus benar-benar ada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            val response = apiService.delMahasiswa(id)
            if (response.isSuccessful) {
                val deleteMahasiswaResponse = response.body()
                if (deleteMahasiswaResponse != null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            mContext,
                            "Berhasil menghapus silahakan cek data pada halaman list!",
                            Toast.LENGTH_SHORT
                        ).show()
                        emptyData()
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("", "onFailure: " + response.body()!!.message)
                    }
                }
            }
        }
    }
    suspend fun searchByNrp(nrp: String) {
        if (nrpSearch.isEmpty()) {
            Toast.makeText(
                mContext,
                "Isikan NRP terlebih dahulu",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            try {
                val response = apiService.getMahasiswa(nrpSearch)
                if (response.isSuccessful) {
                    val searchMahasiswaResponse = response.body()
                    if (searchMahasiswaResponse != null) {
                        mahasiswaList = searchMahasiswaResponse.data
                        if (mahasiswaList.isNullOrEmpty()) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    mContext,
                                    "NRP yang dicari tidak tersedia",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            emptyData()
                        } else {
                            setData(mahasiswaList)
                        }
                    }
                } else {
                    Log.e("API Error", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API Error", "Exception: ${e.localizedMessage}")
            }
        }
    }

//    fun deleteMahasiswa() {
//        if (id != "") {
//            val client: Call<AddMahasiswaResponse?>? = apiService.delMahasiswa(id = id)
//            if (client != null) {
//                client.enqueue(object : Callback<AddMahasiswaResponse?> {
//                    override fun onResponse(
//                        call: Call<AddMahasiswaResponse?>,
//                        response: Response<AddMahasiswaResponse?>
//                    ) {
//                        if (response.isSuccessful()) {
//                            if (response.body() != null) {
//                                Toast.makeText(
//                                    mContext,
//                                    "Berhasil menghapus silahakan cek data pada halaman list !",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                emptyData()
//                            }
//                        } else {
//                            if (response.body() != null) {
//                                Log.e("", "onFailure: " + response.message())
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<AddMahasiswaResponse?>, t: Throwable) {
//                        Log.e("Error Retrofit", "onFailure: " + t.message)
//                    }
//                })
//            }
//        } else {
//            Toast.makeText(
//                mContext,
//                "Pastikan Mahasiswa yang dihapus benar-benar ada",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }



//    fun searchWithNrp(nrp: String) {
//        if (nrpSearch.isEmpty()) {
//            Toast.makeText(
//                mContext,
//                "Isikan NRP terlebih dahulu",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            val client = apiService.getMahasiswa(nrpSearch)
//            if (client != null) {
//                client.enqueue(object : Callback<MahasiswaResponse?> {
//                    override fun onResponse(
//                        call: Call<MahasiswaResponse?>,
//                        response: Response<MahasiswaResponse?>
//                    ) {
//                        if (response.isSuccessful) {
//                            if (response.body() != null) {
//                                mahasiswaList = response.body()!!.data
//                                if (mahasiswaList?.isEmpty() == true) {
//                                    Toast.makeText(
//                                        mContext,
//                                        "NRP yang dicari tidak tersedia",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                } else {
//                                    setData(mahasiswaList)
//                                }
//                            }
//                        } else {
//                            if (response.body() != null) {
//                                Log.e("", "onFailure: " + response.message())
//
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<MahasiswaResponse?>, t: Throwable) {
//                        Log.e(
//                            "Error Retrofit",
//                            "onFailure: " + t.message
//                        )
//                    }
//                })
//            }
//        }
//    }

//    val mahasiswa = Mahasiswa(
//        nama = "Andra",
//        nrp = "225150701111003",
//        email = "andradzaki@student.ub.ac.id",
//        jurusan = "Teknologi Informasi",
//        id = "0"
//    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nrpSearch,
            onValueChange = { nrpSearch = it },
            label = {
                Text(
                    text = "Masukkan NRP mahasiswa yang ingin dicari", color = Color.Gray
                )
            })
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("insertupdatepage") }) {
            Text(text = "Edit Data")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                coroutineScope.launch(Dispatchers.IO) { searchByNrp(nrp = nrpSearch) }
            }
        ) {
            Text(text = "Cari Mahasiswa")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Red),
            onClick = {
                coroutineScope.launch(Dispatchers.IO) { deleteMahasiswa() }
            }
        ) {
            Text(text = "Hapus Mahasiswa")
        }
        Column(modifier = Modifier.padding(10.dp)) {
            InfoRow(label = "NRP", value = nrp)
            InfoRow(label = "Nama", value = name)
            InfoRow(label = "Email", value = email)
            InfoRow(label = "Jurusan", value = major)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPagePreview() {
    SearchPage(navController = rememberNavController())
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly // Space between label and value
    ) {
        Text(text = label, modifier = Modifier.weight(1f), fontSize = 18.sp) // Label text
        Text(text = ":  $value", modifier = Modifier.weight(4f), fontSize = 18.sp) // Value text
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoRowPreview() {
    InfoRow(label = "Nama", value = "Andra")
}