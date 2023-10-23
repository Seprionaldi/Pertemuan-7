package com.example.apicrud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apicrud.Models.ModelPetani

class AdapterPetani(private val context: Context, private var petaniList: MutableList<ModelPetani>) :
    RecyclerView.Adapter<AdapterPetani.PetaniViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetaniViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_petani, parent, false)
        return PetaniViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetaniViewHolder, position: Int) {
        val petani = petaniList[position]
        holder.bind(petani)
    }

    override fun getItemCount(): Int {
        return petaniList.size
    }

    fun updateData(newData: List<ModelPetani>) {
        petaniList.clear()
        petaniList.addAll(newData)
        notifyDataSetChanged()
    }

    inner class PetaniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val namaTextView: TextView = itemView.findViewById(R.id.nama)
        private val alamatTextView: TextView = itemView.findViewById(R.id.alamat)
        private val provinsiTextView: TextView = itemView.findViewById(R.id.provinsi)
        private val kabupatenTextView: TextView = itemView.findViewById(R.id.Kabupaten)
        private val kecamatanTextView: TextView = itemView.findViewById(R.id.kacamatan)
        private val kelurahanTextView: TextView = itemView.findViewById(R.id.kelurahan)
        private val namaIstriTextView: TextView = itemView.findViewById(R.id.nama_istri)
        private val jumlahLahanTextView: TextView = itemView.findViewById(R.id.jumlah_lahan)
        private val fotoTextView: TextView = itemView.findViewById(R.id.foto)

        fun bind(petani: ModelPetani) {
            namaTextView.text = "Nama: ${petani.nama}"
            alamatTextView.text = "Alamat: ${petani.alamat}"
            provinsiTextView.text = "Provinsi: ${petani.provinsi}"
            kabupatenTextView.text = "Kabupaten: ${petani.Kabupaten}"
            kecamatanTextView.text = "Kecamatan: ${petani.kacamatan}"
            kelurahanTextView.text = "Kelurahan: ${petani.kelurahan}"
            namaIstriTextView.text = "Nama Istri: ${petani.namaIstri}"
            jumlahLahanTextView.text = "Jumlah Lahan: ${petani.jumlahLahan}"
            fotoTextView.text = "Foto: ${petani.foto}"
        }
    }

}
