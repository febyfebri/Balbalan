package com.feby.balbalan.view.listteam

import com.feby.balbalan.model.Team

interface TeamContract {
    interface View{
        fun displayTeams(teamList: List<Team>)
        fun hideLoading()
        fun showLoading()

    }
    interface Presenter{
        fun getTeamData(leagueName: String = "4328")
        fun onDestroy()
    }
}