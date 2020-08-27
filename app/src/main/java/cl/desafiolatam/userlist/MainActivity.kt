package cl.desafiolatam.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.userlist.model.pojo.User
import cl.desafiolatam.userlist.model.pojo.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity () {
    private var usersList = ArrayList<User>()
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    override fun onCreate (savedInstanceState: Bundle ?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAdapter = UserAdapter(usersList)
        usersRecyclerView.adapter = viewAdapter
        loadApiData()
    }
    private fun loadApiData () {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPosts()
        call.enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse (
                call: Call < ArrayList < User >>,
                response: Response < ArrayList < User >>
            ) {
                response.body()?.map {
                    Log.d( "MAIN" , " ${it.id} - ${it.name} - ${it.email} - ${it.phone}" )
                    usersList.add(it) // Agregamos cada post a la lista de post
                }
                viewAdapter.notifyDataSetChanged()
            }
            override fun onFailure (call: Call < ArrayList < User >>, t: Throwable ) {
                Log.d( "MAIN" , "Error: " + t)
                Toast.makeText(
                    applicationContext,
                    "Error: no pudimos recuperar los posts desde el api" ,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}

   /* private fun loadApiData () {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPosts()
        call.enqueue( object : Callback<ArrayList<Post>> {
            override fun onResponse (
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                response.body()?.map {
                    Log.d( "MAIN" , " ${it.id} - ${it.title} " )
                }
            }
            override fun onFailure (call: Call < ArrayList < Post >>, t: Throwable ) {
                Log.d( "MAIN" , "Error: " + t)
                Toast.makeText(
                    applicationContext,
                    "Error: no pudimos recuperar los posts desde el api" ,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }*/



