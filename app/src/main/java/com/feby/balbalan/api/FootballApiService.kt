package com.feby.balbalan.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.feby.balbalan.BuildConfig.BASE_URL
import com.feby.balbalan.BuildConfig.TSDB_API_KEY

class FootballApiService {

    companion object {
        fun getClient() : Retrofit{
            return Retrofit.Builder()
                    .baseUrl("$BASE_URL$TSDB_API_KEY/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}