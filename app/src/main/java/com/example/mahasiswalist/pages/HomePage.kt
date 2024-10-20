package com.example.mahasiswalist.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mahasiswalist.model.Mahasiswa
import com.example.mahasiswalist.model.MahasiswaResponse
import retrofit2.Call

val mahasiswa = Mahasiswa()

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController) {
    LaunchedEffect(Unit) {

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(5) { index ->
                MahasiswaRow(mahasiswa = mahasiswa)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate("insertupdatepage") }) {
            Text(text = "Tambah")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate("searchpage") }) {
            Text(text = "Cari Mahasiswa")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePagePreview() {
    HomePage(navController = rememberNavController())
}

@Composable
fun MahasiswaRow(modifier: Modifier = Modifier, mahasiswa: Mahasiswa) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Nama: ${mahasiswa.nama}")
            Text(text = "NRP: ${mahasiswa.nrp}")
            Text(text = "Email: ${mahasiswa.email}")
            Text(text = "Jurusan: ${mahasiswa.jurusan}")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = Color.Red,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MahasiswaRowPreview() {
    MahasiswaRow(mahasiswa = mahasiswa)
}