package com.feby.balbalan.view.listteam

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_team.*
import com.feby.balbalan.R
import com.feby.balbalan.adapter.TeamAdapter
import com.feby.balbalan.extensions.hide
import com.feby.balbalan.extensions.show
import com.feby.balbalan.model.Team
import com.feby.balbalan.model.repository.TeamRepositoryImpl
import com.feby.balbalan.api.FootballApiService
import com.feby.balbalan.api.FootballRest
import com.feby.balbalan.utils.AppSchedulerProvider


class TeamFragment : Fragment(), TeamContract.View {

    lateinit var leagueName : String
    lateinit var mPresenter : TeamContract.Presenter

    private var teamLists : MutableList<Team> = mutableListOf()

    override fun displayTeams(teamList: List<Team>) {
        teamLists.clear()
        teamLists.addAll(teamList)
        val layoutManager = GridLayoutManager(context, 3)
        rvTeam.layoutManager = layoutManager
        rvTeam.adapter = TeamAdapter(teamLists, context)
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvTeam.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvTeam.visibility = View.INVISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballApiService.getClient().create(FootballRest::class.java)
        val request = TeamRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        setHasOptionsMenu(true)
        mPresenter = TeamPresenter(this, request, scheduler)
        mPresenter.getTeamData("4328")
        val spinnerItems = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, spinnerItems) }
        spinnerTeam.adapter = spinnerAdapter
        spinnerTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinnerTeam.selectedItem.toString()
                when(leagueName){
                    getString(R.string.english_league) -> mPresenter.getTeamData(getString(
                        R.string.english_league_id
                    ))
                    getString(R.string.german_league) -> mPresenter.getTeamData(getString(
                        R.string.german_league_id
                    ))
                    getString(R.string.italian_league) -> mPresenter.getTeamData(getString(
                        R.string.italian_league_id
                    ))
                    getString(R.string.french_league) -> mPresenter.getTeamData(getString(
                        R.string.french_league_id
                    ))
                    getString(R.string.spanish_league) -> mPresenter.getTeamData(getString(
                        R.string.spanish_league_id
                    ))
                    getString(R.string.netherland_league) -> mPresenter.getTeamData(getString(
                        R.string.netherland_league_id
                    ))
                    else -> mPresenter.getTeamData()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.onDestroy()
    }
}
