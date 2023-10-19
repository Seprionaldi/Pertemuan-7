package com.example.apicrud.Activity

import AdapterPetani
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicrud.API.RetroServer
import com.example.apicrud.Models.ModelPetani
import com.example.apicrud.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var rvpetani: RecyclerView
    private lateinit var pbpetani: ProgressBar
    private lateinit var fabTambah: FloatingActionButton

    private lateinit var adPetani: RecyclerView.Adapter<*>
    private lateinit var lmPetani: RecyclerView.LayoutManager
    private val listpetani: MutableList<ModelPetani> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvpetani = findViewById(R.id.rvpetani)
        pbpetani = findViewById(R.id.pb_petani)
        fabTambah = findViewById(R.id.tab_tambah)

        lmPetani = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvpetani.layoutManager = lmPetani

        rvpetani.adapter = adPetani

        fetchDataFromApi()


        fabTambah.setOnClickListener {
            startActivity(Intent(this, TambahActivity::class.java))


        }

    }

    private fun fetchDataFromApi() {
        pbpetani.visibility = View.VISIBLE

        val apiService = RetroServer.create() // Ganti dengan instance layanan Retrofit Anda

        val call = apiService.getDaftarPetani()
        call.enqueue(object : Callback<List<ModelPetani>> {
            override fun onResponse(call: Call<List<ModelPetani>>, response: Response<List<ModelPetani>>) {
                if (response.isSuccessful) {
                    val petaniList = response.body()

                    // Inisialisasi adapter dengan daftar petani
                    adPetani = AdapterPetani(this@MainActivity, petaniList) // Perhatikan penggunaan `this@MainActivity` untuk konteks

                    // Set adapter ke RecyclerView
                    rvpetani.adapter = adPetani

                    // Sampaikan bahwa ada perubahan dalam data
                    adPetani.notifyDataSetChanged()

                    // Sembunyikan ProgressBar
                    pbpetani.visibility = View.GONE

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
                pbpetani.visibility = View.GONE
            }
        })
    }
}