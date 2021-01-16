package com.feby.balbalan.view.favoritematch

import com.feby.balbalan.model.Event

interface FavoriteMatchContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList:List<Event>)
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getFootballMatchData()
        fun onDestroyPresenter()
    }
}