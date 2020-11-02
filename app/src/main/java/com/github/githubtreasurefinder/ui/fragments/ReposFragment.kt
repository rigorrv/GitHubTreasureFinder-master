package com.pany.withrooms.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.githubtreasurefinder.R
import com.pany.withrooms.adapters.ReposAdapter
import com.pany.withrooms.model.repos.Item
import com.pany.withrooms.model.repos.ReposFavotire
import com.pany.withrooms.ui.Communicator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.repos_fragment.*
import kotlinx.android.synthetic.main.repos_fragment.repoSearch
import kotlinx.android.synthetic.main.repos_fragment.view.*
import kotlinx.android.synthetic.main.users_fragment.*

lateinit var communicator: Communicator
class ReposFragment : Fragment(R.layout.repos_fragment) {

    lateinit var rootView : View
    val reposAdapter = ReposAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        communicator = activity as Communicator
        rootView = inflater.inflate(R.layout.repos_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 3)
                    {
                        communicator.getRepo(repoSearch.text.toString())
                    }
                if (s.length >=2){
                    communicator.hideImage()
                    communicator.loadingON()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun reposList(reposList : List<Item>){
        reposAdapter.reposList = reposList
        rootView.reposRecycler.adapter = reposAdapter
    }
    fun favoriteList(reposList : List<ReposFavotire>){
        reposAdapter.favoriteList = reposList
        rootView.reposRecycler.adapter = reposAdapter
    }

}
