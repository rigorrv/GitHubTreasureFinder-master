package com.pany.withrooms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.daggerretrofit.model.UserInformation
import com.github.githubtreasurefinder.R
import com.pany.withrooms.database.repos.FavoriteFactory
import com.pany.withrooms.database.repos.ViewModelRepoFactory
import com.pany.withrooms.model.repos.Item
import com.pany.withrooms.model.repos.ReposFavotire
import com.pany.withrooms.ui.Communicator
import com.pany.withrooms.viewmodel.FavoriteViewModel
import com.pany.withrooms.viewmodel.UsersViewModel
import com.pany.withrooms.viewmodel.ReposViewModel
import com.pany.withrooms.ui.fragments.ReposFragment
import com.pany.withrooms.ui.fragments.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var myViewModelOld: UsersViewModel
    private lateinit var repoViewModel: ReposViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    val userFragment = UserFragment()
    val reposFragment = ReposFragment()
    val TAG: String = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.mainFragment, reposFragment)
                .commit()
        backBtn.setOnClickListener {
            Log.d(TAG, "onCreate: Back")
            supportFragmentManager.beginTransaction().replace(R.id.mainFragment, reposFragment)
                    .commit()
        }
    }

    override fun getUser(user: String, avatar: String) {
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, userFragment)
                .addToBackStack(null)
                .commit()
        myViewModelOld = ViewModelProvider(this).get(UsersViewModel::class.java)
        myViewModelOld.getUser(user)
        var userObserver = Observer<UserInformation> {
            userFragment.getUserList(it, user, avatar)
        }

        myViewModelOld.userLiveData.observe(this, userObserver)
    }

    override fun getRepo(repo: String) {
        repoViewModel = ViewModelProvider(this, ViewModelRepoFactory(this)).get(ReposViewModel::class.java)
        repoViewModel.getRepo(repo)
        favoriteViewModel = ViewModelProvider(this, FavoriteFactory(this)).get(FavoriteViewModel::class.java)
        favoriteViewModel.checkFavorite()
        favoriteViewModel.deletRepo()
        //loading network data
        var repoObserver = Observer<List<Item>> {
            for (item in it) {
                favoriteViewModel.insertRepos(item)
                favoriteViewModel.insertOwner(item.owner)
            }
        }

        var favoritesObserver = Observer<List<ReposFavotire>> {
            for (item in it) {
                var repoObserver = Observer<List<Item>> {
                    for (itemRepo in it) {
                        if (itemRepo.name == item.repo) {
                            favoriteViewModel.updateItem(true, item.repo)
                            //getRepos()
                        }
                    }
                }
                repoViewModel.repoLiveData.observe(this, repoObserver)
            }
            getRepos()
        }


        repoViewModel.repoLiveData.observe(this, repoObserver)
        //loading favorites

        favoriteViewModel.favoritesLiveData.observe(this, favoritesObserver)

    }

    override fun getRepos() {
        Log.d(TAG, "getRepo: Searching")
        favoriteViewModel.checkItems()
        var checkRepos = Observer<List<Item>> {
            reposFragment.reposList(it)
        }
        favoriteViewModel.repoLiveData.observe(this, checkRepos)


    }

    override fun saveFavorites(favorites: Item) {
        favoriteViewModel = ViewModelProvider(this, FavoriteFactory(this)).get(FavoriteViewModel::class.java)
        favoriteViewModel.saveFavorite(favorites)
    }

    override fun deletFavorite(repo: String) {
        favoriteViewModel = ViewModelProvider(this, FavoriteFactory(this)).get(FavoriteViewModel::class.java)
        favoriteViewModel.deletFavorite(repo)
    }

    override fun hideImage() {
        centerImage.visibility = View.GONE
    }

    override fun loadingON() {
        progressBar.visibility = View.VISIBLE
    }

    override fun loadingOff() {
        progressBar.visibility = View.GONE
    }

    override fun backBtnOn() {
        backBtn.visibility = View.VISIBLE

    }

    override fun backBtnOff() {
        backBtn.visibility = View.GONE
    }
}