package com.pany.withrooms.ui

import com.pany.withrooms.model.repos.Item

interface Communicator {

    fun getUser (user : String, avatar : String)
    fun getRepo (repo : String)
    fun saveFavorites (favorites : Item)
    fun deletFavorite (repo : String)
    fun getRepos()
    fun hideImage()
    fun loadingON()
    fun loadingOff()
    fun backBtnOn()
    fun backBtnOff()

}