package com.feby.balbalan.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import com.feby.balbalan.model.*


interface FootballRest {

    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id:String) : Flowable<FootballMatch>

    @GET("eventsnextleague.php")
    fun getUpcomingMatch(@Query("id") id:String) : Flowable<FootballMatch>

    @GET("lookupevent.php")
    fun getEventById(@Query("id") id:String) : Flowable<FootballMatch>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id:String) : Flowable<Teams>

    @GET("lookup_all_teams.php")
    fun getAllTeam(@Query("id") id:String) : Flowable<Teams>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") id:String) : Flowable<Leagues>

    @GET("lookuptable.php")
    fun getStanding(@Query("l") l:String) : Flowable<Standings>

}