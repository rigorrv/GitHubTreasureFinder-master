package com.pany.withrooms.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pany.withrooms.adapters.ReposAdapter
import com.pany.withrooms.database.repos.ReposDao
import com.pany.withrooms.model.repos.Item
import com.pany.withrooms.model.repos.Owner
import com.pany.withrooms.model.repos.ReposFavotire
import com.pany.withrooms.viewmodel.MyViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteViewModel(private val reposDao: ReposDao) : MyViewModel() {

    var repoLiveData = MutableLiveData<List<Item>>()
    var favoritesLiveData = MutableLiveData<List<ReposFavotire>>()
    val adater = ReposAdapter()

    val TAG: String = "TAG"

    fun insertRepos(item: Item) {
        GlobalScope.launch {
            reposDao.insertRepo(item)
        }
    }
    fun insertOwner(owner : Owner){
        GlobalScope.launch {
            reposDao.insertOwner(owner)
        }
    }
    fun deletRepo(){
        GlobalScope.launch {
            reposDao.deletItem()
        }
    }


    fun saveFavorite(favorite: Item) {
        GlobalScope.launch {
            Log.d(TAG, "Saved")
            reposDao.insertFavorite(ReposFavotire(favorite.name))
        }
    }

    fun updateItem(favorite: Boolean, repo : String) {
        GlobalScope.launch {
            reposDao.updateSave(favorite, repo)
        }
    }

    fun deletFavorite(repo: String) {
        GlobalScope.launch {
            Log.d(TAG, "Removed")
            reposDao.deletFavorite(repo)
        }
    }

    fun checkFavorite() {
        GlobalScope.launch {
            favoritesLiveData.postValue(reposDao.allReposFavorite)

        }
    }
    fun checkItems(){
        GlobalScope.launch {
            repoLiveData.postValue(reposDao.allRepos)
        }
    }

}