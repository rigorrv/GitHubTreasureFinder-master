package com.pany.withrooms.injection

import com.pany.withrooms.networking.Api
import dagger.Module
import dagger.Provides
import dagger.Reusable
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun apiProvides(retrofit: Retrofit):Api{
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun retrofitProvides():Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor).build()
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
    }

}