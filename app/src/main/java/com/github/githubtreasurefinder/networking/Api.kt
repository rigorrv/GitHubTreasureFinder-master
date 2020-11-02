package com.pany.withrooms.networking

import com.github.daggerretrofit.model.UserInformation
import com.pany.withrooms.model.repos.RepoData
import com.pany.withrooms.model.users.UserData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface Api {


    @GET("/search/repositories")
    fun getRepos(
            @Query("q") query: String)
            : Observable<RepoData>

    @GET("/search/users")
    fun getUsers(
            @Query("q") query: String)
            : Observable<UserData>


    @GET("/users/{user}/repos")
    fun getUsersInformation(
            @Path("user") query: String): Observable<UserInformation>
}