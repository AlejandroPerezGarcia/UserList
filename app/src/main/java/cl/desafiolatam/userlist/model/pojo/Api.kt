package cl.desafiolatam.userlist.model.pojo

import retrofit2.Call
import retrofit2.http.GET


interface Api{
    @GET("/posts")
    fun getAllPosts() : Call<ArrayList<Post>>
}