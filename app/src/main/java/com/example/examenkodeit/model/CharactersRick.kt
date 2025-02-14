package com.example.examenkodeit.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
 data class CharactersRick(

val info: Info,
val results: List<Result>,
)

data class Info(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any?,
)
@Parcelize
data class Result(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
) : Parcelable
@Parcelize
data class Origin(
    val name: String,
    val url: String,
) : Parcelable

@Parcelize
data class Location(
    val name: String,
    val url: String,
) : Parcelable
