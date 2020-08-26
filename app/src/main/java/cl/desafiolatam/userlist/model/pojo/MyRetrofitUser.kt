package cl.desafiolatam.userlist.model.pojo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://jsonplaceholder.typicode.com"

interface UserAPI{

    @GET("/users")
    fun getUsers(): Call<List<User>>

    @GET("/users/{id}")
    fun getUsers(@Path("id")postId : Int): Call<User>
}

class RetrofitClient{

    companion object{

        fun retrofitIntance(): UserAPI {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UserAPI::class.java)

        }

    }
}