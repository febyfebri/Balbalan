package com.feby.balbalan.model.repository

import com.feby.balbalan.model.db.FavoriteMatch
import com.feby.balbalan.model.db.FavoriteTeam

interface LocalRepository {

    fun getMatchFromDb() : List<FavoriteMatch>

    fun insertData(eventId: String, homeId: String, awayId: String)

    fun deleteData(eventId: String)

    fun checkFavorite(eventId: String) : List<FavoriteMatch>

    fun getTeamFromDb() : List<FavoriteTeam>

    fun insertTeamData(teamId: String, imgUrl: String)

    fun deleteTeamData(teamId: String)

    fun checkFavTeam(teamId: String) : List<FavoriteTeam>
}