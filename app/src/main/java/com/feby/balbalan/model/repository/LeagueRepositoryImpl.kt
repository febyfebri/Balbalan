package com.feby.balbalan.model.repository

import io.reactivex.Flowable
import com.feby.balbalan.api.FootballRest
import com.feby.balbalan.model.Leagues

class LeagueRepositoryImpl(private val footballRest: FootballRest):
    LeagueRepository {

    override fun getLeagueData(id: String): Flowable<Leagues> = footballRest.getDetailLeague(id)

}