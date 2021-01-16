package com.feby.balbalan.model.repository


import io.reactivex.Flowable
import com.feby.balbalan.model.Teams

interface TeamRepository {

    fun getTeams(id : String = "0") : Flowable<Teams>

    fun getTeamsDetail(id : String = "0") : Flowable<Teams>

    fun getAllTeam(id: String) : Flowable<Teams>

}