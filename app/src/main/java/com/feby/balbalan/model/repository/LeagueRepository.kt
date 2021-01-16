package com.feby.balbalan.model.repository

import io.reactivex.Flowable
import com.feby.balbalan.model.Leagues

interface LeagueRepository {

    fun getLeagueData(id: String) : Flowable<Leagues>

}