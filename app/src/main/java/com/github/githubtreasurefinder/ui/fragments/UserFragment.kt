package com.pany.withrooms.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.daggerretrofit.model.UserInformation
import com.github.githubtreasurefinder.R
import com.pany.withrooms.adapters.UserAdapter
import com.pany.withrooms.ui.Communicator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.users_fragment.*
import kotlinx.android.synthetic.main.users_fragment.view.*

lateinit var avatarUrl : String
lateinit var userName : String
class UserFragment:Fragment() {

    lateinit var rootView : View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        communicator = activity as Communicator
        rootView = inflater.inflate(R.layout.users_fragment, container, false)
        return  rootView
    }



    fun getUserList(reposList: UserInformation, user: String, avatar : String){
        userRecycler?.adapter = UserAdapter(reposList)
        userName = user
        avatarUrl = avatar

        nameUser?.text = userName
        Picasso.get()
                .load(avatarUrl)
                .into(rootView.avatarUser)

    }


}