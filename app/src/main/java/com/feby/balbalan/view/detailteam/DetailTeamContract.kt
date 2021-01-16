package com.feby.balbalan.view.detailteam

import com.feby.balbalan.model.db.FavoriteTeam

interface TeamDetailContract {

    interface View{
        fun setFavoriteState(favList:List<FavoriteTeam>)
    }

    interface Presenter{
        fun deleteTeam(id:String)
        fun checkTeam(id:String)
        fun insertTeam(id: String, imgUrl: String)
    }
}