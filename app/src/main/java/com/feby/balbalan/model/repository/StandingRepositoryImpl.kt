package com.feby.balbalan.model.repository

import io.reactivex.Flowable
import com.feby.balbalan.model.Standings
import com.feby.balbalan.api.FootballRest

class StandingRepositoryImpl(private val footballRest: FootballRest):
    StandingRepository {

    override fun getStanding(l: String): Flowable<Standings> = footballRest.getStanding(l)

}