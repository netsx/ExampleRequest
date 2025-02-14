package com.example.examenkodeit.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenkodeit.AdapterListCharacter
import com.example.examenkodeit.R
import com.example.examenkodeit.RetrofitClient
import com.example.examenkodeit.model.Result
import com.example.examenkodeit.databinding.ActivityDetailBinding
import com.example.examenkodeit.model.CharacterId
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        fetchCharactersbyId()

    }

    private fun fetchCharactersbyId() {
        val character = intent.getParcelableExtra<Result>("CHARACTER")
        if (character != null) {
            RetrofitClient.getInstance().getCharacterbyId("${character.id}").enqueue(object :Callback<CharacterId>{
                @SuppressLint("SetTextI18n")
                override fun onResponse(p0: Call<CharacterId>, p1: Response<CharacterId>) {
                    p1.body()?.let { apiResponse ->

                        binding.root.findViewById<TextView>(R.id.name).text = "Nombre: "+character.name
                        binding.root.findViewById<TextView>(R.id.status).text = "Estatus: "+character.status
                        binding.root.findViewById<TextView>(R.id.especie).text = "Especie: "+character.species
                        binding.root.findViewById<TextView>(R.id.genero).text = "Genero: "+character.gender
                        binding.root.findViewById<TextView>(R.id.origen).text = "Origen: "+character.origin.name +"\n"+ character.origin.url
                        binding.root.findViewById<TextView>(R.id.locacion).text = "Ubicacion: "+character.location.name +"\n"+ character.origin.url

                        val ima =binding.root.findViewById<ImageView>(R.id.imageCharacterId)
                        val lista= binding.root.findViewById<RecyclerView>(R.id.recyclerEpisodes)
                        lista.layoutManager = LinearLayoutManager(this@DetailActivity)
                        Picasso.get()
                            .load(character.image)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_background)
                            .into(ima)

                        lista.adapter= AdapterListEpisodes(apiResponse.episode )
                        Log.e("EXITO", " ${apiResponse.name}}")

                    }
                }

                override fun onFailure(p0: Call<CharacterId>, p1: Throwable) {
                    Log.e("API_ERROR11", "Error en la respuesta: ${p1.message} ${  p1.cause}")

                }
            })
        }
    }
}


