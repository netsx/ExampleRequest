package com.example.examenkodeit
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenkodeit.model.Result
import com.example.examenkodeit.detail.DetailActivity
import com.example.examenkodeit.model.CharactersRick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private var ar = ArrayList<Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchCharacters()
    }
    private fun fetchCharacters() {
        RetrofitClient.getInstance().getCharacter().enqueue(object : Callback<CharactersRick> {
            override fun onResponse(p0: Call<CharactersRick>, p1: Response<CharactersRick>) {
               if(p1.isSuccessful){


                   p1.body()?.let { apiResponse ->
                            ar = apiResponse.results as ArrayList<Result>
                           recyclerView.adapter = AdapterListCharacter(apiResponse.results, this@MainActivity )
                       }

               }else{
                   Log.e("API_ERROR", "Error en la respuesta: ${p1.code()} ${  p1.message()}")
               }
            }

            override fun onFailure(p0: Call<CharactersRick>, p1: Throwable) {
                Log.e("API_ERROR", "Error en la respuesta: ${p1.message}")
            }


        })
    }

    override fun onItemClick(position: Int ) {
        Log.e("ONCLICK", "Error en la respuesta: $position")
        val content = ar[position]
        val bundle = Bundle()
        val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("CHARACTER", content)
               }
        startActivity(intent)
    }
}