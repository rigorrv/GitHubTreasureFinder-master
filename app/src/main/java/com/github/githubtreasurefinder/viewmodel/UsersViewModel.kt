package com.pany.withrooms.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.daggerretrofit.model.User
import com.github.daggerretrofit.model.UserInformation
import com.pany.withrooms.networking.Api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class UsersViewModel : MyViewModel() {


    @Inject
    lateinit var api: Api
    var userLiveData = MutableLiveData<UserInformation>()


    @Throws(IllegalAccessException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 nonstopcode.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }

    fun getUser(users: String) {
        api.getUsersInformation(users)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    userLiveData.value = result
                },
                        { error ->
                            Log.d("TAG", "getUser: $error")
                        })
    }
}