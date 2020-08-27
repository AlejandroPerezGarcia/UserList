package cl.desafiolatam.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.userlist.model.pojo.Post
import cl.desafiolatam.userlist.model.pojo.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity () {
    private var postsList = ArrayList<Post>()
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    override fun onCreate (savedInstanceState: Bundle ?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAdapter = PostAdapter(postsList)
        postsRecyclerView.adapter = viewAdapter
        loadApiData()
    }
    private fun loadApiData () {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPosts()
        call.enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse (
                call: Call < ArrayList < Post >>,
                response: Response < ArrayList < Post >>
            ) {
                response.body()?.map {
                    Log.d( "MAIN" , " ${it.id} - ${it.title} " )
                    postsList.add(it) // Agregamos cada post a la lista de post
                }
                viewAdapter.notifyDataSetChanged()
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



