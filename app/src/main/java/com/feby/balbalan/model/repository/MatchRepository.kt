package com.feby.balbalan.model.repository

import io.reactivex.Flowable
import com.feby.balbalan.model.FootballMatch

interface MatchRepository {

    fun getFootballMatch(id : String) : Flowable<FootballMatch>

    fun getUpcomingMatch(id : String) : Flowable<FootballMatch>

    fun getEventById(id: String) : Flowable<FootballMatch>
}