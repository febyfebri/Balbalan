package com.feby.balbalan.model.repository

import io.reactivex.Flowable
import com.feby.balbalan.model.Standings

interface StandingRepository {

    fun getStanding(l: String) : Flowable<Standings>

}