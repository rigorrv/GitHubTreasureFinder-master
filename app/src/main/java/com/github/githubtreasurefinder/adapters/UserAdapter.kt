package com.pany.withrooms.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.daggerretrofit.model.UserInformation
import com.github.githubtreasurefinder.R
import com.pany.withrooms.ui.fragments.communicator
import kotlinx.android.synthetic.main.user_items.view.*
import kotlinx.android.synthetic.main.users_fragment.view.*


var repoFav = ""
class UserAdapter(val userList : UserInformation) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(userList[position])
        communicator.loadingOff()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val favoriteUser = itemView.favoriteUser
        val userName = itemView.userName

        fun onBind(userList: UserInformation.UserInformationItem){
            userName.text = userList.name

            //imageText.text = repoList.favorite.toString()
            if(userList.name == repoFav){
                favoriteUser.setBackgroundResource(R.drawable.star_on)
            }else{
                favoriteUser.setBackgroundResource(R.drawable.star_off_alpha)

            }

            itemView.setOnClickListener {
                Log.d("TAG", "onBind: ${userList.name}")
            }

        }

    }

}
