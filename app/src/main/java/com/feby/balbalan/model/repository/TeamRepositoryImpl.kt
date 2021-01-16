package com.feby.balbalan.model.repository


import io.reactivex.Flowable
import com.feby.balbalan.api.FootballRest
import com.feby.balbalan.model.Teams


class TeamRepositoryImpl(private val footballRest: FootballRest) :
    TeamRepository {

    override fun getAllTeam(id: String) = footballRest.getAllTeam(id)
    override fun getTeams(id: String): Flowable<Teams> = footballRest.getAllTeam(id)
    override fun getTeamsDetail(id: String): Flowable<Teams> = footballRest.getTeam(id)

}