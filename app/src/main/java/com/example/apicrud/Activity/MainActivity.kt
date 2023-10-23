package com.example.apicrud.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicrud.API.RetroServer
import com.example.apicrud.AdapterPetani
import com.example.apicrud.Models.ModelPetani
import com.example.apicrud.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rvPetani: RecyclerView
    private lateinit var pbPetani: ProgressBar
    private lateinit var adPetani: AdapterPetani
    private lateinit var lmPetani: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPetani = findViewById(R.id.rvpetani)
        pbPetani = findViewById(R.id.pb_petani)

        lmPetani = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPetani.layoutManager = lmPetani

        // Inisialisasi AdapterPetani dengan daftar kosong
        val petaniList = mutableListOf<ModelPetani>()
        adPetani = AdapterPetani(this, petaniList)
        rvPetani.adapter = adPetani

        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        pbPetani.visibility = View.VISIBLE

        val apiService = RetroServer.create()

        val call = apiService.getDaftarPetani()
        call.enqueue(object : Callback<List<ModelPetani>> {
            override fun onResponse(call: Call<List<ModelPetani>>, response: Response<List<ModelPetani>>) {
                if (response.isSuccessful) {
                    val petaniList = response.body()?.toMutableList() ?: mutableListOf()

                    // Perbarui adapter dengan daftar yang diperbarui
                    adPetani.updateData(petaniList)

                    // Sembunyikan ProgressBar
                    pbPetani.visibility = View.GONE
                } else {
                    // Tangani jika respons tidak berhasil
                    // Misalnya, tampilkan pesan kesalahan
                    Toast.makeText(this@MainActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ModelPetani>>, t: Throwable) {
                // Tangani jika ada kesalahan jaringan atau lainnya
                // Misalnya, tampilkan pesan kesalahan
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()

                // Sembunyikan ProgressBar
                pbPetani.visibility = View.GONE
            }
        })
    }
}
