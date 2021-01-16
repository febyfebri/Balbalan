package com.feby.balbalan.model

import com.google.gson.annotations.SerializedName

data class Teams(
    @SerializedName("teams")
    var teams: List<Team>)
