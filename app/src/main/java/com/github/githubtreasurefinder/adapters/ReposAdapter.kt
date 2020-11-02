package com.pany.withrooms.adapters

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.githubtreasurefinder.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pany.withrooms.model.repos.Item
import com.pany.withrooms.model.repos.Owner
import com.pany.withrooms.model.repos.ReposFavotire
import com.pany.withrooms.ui.fragments.communicator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repos_items.view.*


class ReposAdapter() : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    var repo: String = ""
    var favoriteList: List<ReposFavotire> = mutableListOf()
    var reposList: List<Item> = mutableListOf()
    val TAG: String = "TAG"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.repos_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(reposList[position])
        communicator.loadingOff()
        holder.itemView.favoriteBtn.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: ${reposList[position].name}")
            repoFav = reposList[position].name
            communicator.saveFavorites(reposList[position])
            holder.itemView.favoriteBtn.setBackgroundResource(R.drawable.star_on)
            val jsonObj = Gson().toJson(reposList[position].owner)
            val companyType = object : TypeToken<Owner>() {}.type
            val items: Owner = Gson().fromJson(jsonObj, companyType)
            communicator.getUser(items.login, items.avatar_url)


        }
//        communicator.checkRepoFavorite(reposList[position].name)


    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val array = SparseBooleanArray()
        val reposName = itemView.repoName
        val avatar = itemView.avatarRepo
        val scoreText = itemView.userText
        val userText = itemView.userText
        val favBtn = itemView.favoriteBtn


        fun onBind(repoList: Item) {
            reposName.text = repoList.name
            scoreText.text = repoList.score
            var randomImg = (0..3).random()

            val jsonObj = Gson().toJson(repoList.owner)
            val companyType = object : TypeToken<Owner>() {}.type
            val items: Owner = Gson().fromJson(jsonObj, companyType)
            userText.text = items.login

            Picasso.get()
                    .load(items.avatar_url)
                    .into(avatar)

            //imageText.text = repoList.favorite.toString()
            if (repoList.favorite) {
                favBtn.setBackgroundResource(R.drawable.star_on)
            } else {
                favBtn.setBackgroundResource(R.drawable.star_off)

            }

            itemView.setOnClickListener {
                communicator.loadingON()
                repoFav = repoList.name
                communicator.getUser(items.login, items.avatar_url)

            }
        }
    }

}
