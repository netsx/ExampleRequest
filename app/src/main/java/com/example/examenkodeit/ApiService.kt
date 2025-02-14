package com.example.examenkodeit

import com.example.examenkodeit.model.CharacterId
import com.example.examenkodeit.model.CharactersRick
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface ApiService {
    @GET("api/character")
    fun getCharacter(): Call<CharactersRick>

     @GET("api/character/{id}")
     fun getCharacterbyId(@Path("id") id: String): Call<CharacterId>
}