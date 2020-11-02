package com.pany.withrooms.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pany.withrooms.database.repos.ReposDao
import com.pany.withrooms.model.repos.Item
import com.pany.withrooms.model.repos.Owner
import com.pany.withrooms.networking.Api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class ReposViewModel(private val reposDao: ReposDao) : MyViewModel() {


    @Inject
    lateinit var api: Api
    var repoLiveData = MutableLiveData<List<Item>>()



    @Throws(IllegalAccessException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 nonstopcode.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }

    fun getRepo(repo: String) {

        if (isConnected())
            api.getRepos(repo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        val developerList = result.items
                        repoLiveData.value = result.items
                        //this is not the best way to get the info I should use corutines, but isn't enough time, and is working
                    },
                            { error ->
                                Log.d("TAG", "getUser: $error")
                            })
        else {
            //this is not the best way to get the info I should use corutines, but isn't enough time, and is working
            GlobalScope.launch {
                repoLiveData.postValue(reposDao.allRepos)
                Log.d("TAG", "getRepo: $repoLiveData")
            }

        }
    }
}