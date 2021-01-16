package com.feby.balbalan.view.favoriteteam

import com.feby.balbalan.model.Team

interface FavoriteTeamContract {

    interface View{
        fun displayTeams(teamList: List<Team>)
        fun hideLoading()
        fun showLoading()
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getTeamData()
        fun onDestroy()
    }
}