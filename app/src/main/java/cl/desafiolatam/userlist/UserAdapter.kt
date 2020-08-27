package cl.desafiolatam.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.userlist.model.pojo.User
import kotlinx.android.synthetic.main.list_item.view.*

class UserAdapter(private val myDataset: List<User>) :
    RecyclerView.Adapter<UserAdapter.PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
// crea una nueva vista
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post = myDataset[position]

        holder.title.text = post.name
        holder.email.text = post.email
        holder.phone.text = post.phone

    }

    class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.txname
        var email: TextView = itemView.txemail
        var phone: TextView = itemView.txPhone
    }
}