package ir.siriusapps.ghalam.network

import ir.siriusapps.ghalam.data.Note
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Apis {

    @GET("/api/notes")
    fun getNotes(): Call<List<Note>>

    @Headers("Content-Type: application/json")
    @POST("/api/notes")
    fun newNote(@Body note: Note):Call<Note>

}