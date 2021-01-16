package com.feby.balbalan.model.repository

import io.reactivex.Flowable
import com.feby.balbalan.api.FootballRest
import com.feby.balbalan.model.FootballMatch

class MatchRepositoryImpl(private val footballRest: FootballRest) :
    MatchRepository {

    override fun getEventById(id: String): Flowable<FootballMatch> = footballRest.getEventById(id)

    override fun getUpcomingMatch(id: String): Flowable<FootballMatch> = footballRest.getUpcomingMatch(id)

    override fun getFootballMatch(id: String): Flowable<FootballMatch> = footballRest.getLastmatch(id)
}