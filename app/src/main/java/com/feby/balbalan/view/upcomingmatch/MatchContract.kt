package com.feby.balbalan.view.upcomingmatch

import com.feby.balbalan.model.Event
import com.feby.balbalan.model.League


interface MatchContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun showLeague(data:List<League>)
        fun displayFootballMatch(matchList:List<Event>)
    }

    interface Presenter{
        fun getLeagueDetailData(leagueId: String = "4328")
        fun getFootballMatchData(leagueName: String = "4328")
        fun onDestroyPresenter()

    }
}