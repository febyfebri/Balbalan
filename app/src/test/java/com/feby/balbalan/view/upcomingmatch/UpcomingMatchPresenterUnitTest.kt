package com.feby.balbalan.view.upcomingmatch

import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import com.feby.balbalan.model.Event
import com.feby.balbalan.model.FootballMatch
import com.feby.balbalan.model.League
import com.feby.balbalan.model.Leagues
import com.feby.balbalan.model.repository.LeagueRepositoryImpl
import com.feby.balbalan.model.repository.MatchRepositoryImpl
import com.feby.balbalan.utils.SchedulerProvider
import com.feby.balbalan.utils.TestingSchedulerProvider

class UpcomingMatchPresenterUnitTest {

    @Mock
    lateinit var mView: MatchContract.View

    @Mock
    lateinit var matchRepositoryImpl: MatchRepositoryImpl

    @Mock
    lateinit var leagueRepositoryImpl: LeagueRepositoryImpl

    private lateinit var scheduler: SchedulerProvider

    private lateinit var mPresenter: UpcomingMatchPresenter

    private lateinit var match : FootballMatch

    private lateinit var league : Leagues

    private lateinit var footballMatch: Flowable<FootballMatch>

    private lateinit var leagueList: Flowable<Leagues>

    private val event = mutableListOf<Event>()

    private val leagues = mutableListOf<League>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestingSchedulerProvider()
        match = FootballMatch(event)
        league = Leagues(leagues)
        footballMatch = Flowable.just(match)
        leagueList = Flowable.just(league)
        mPresenter = UpcomingMatchPresenter(mView, matchRepositoryImpl, leagueRepositoryImpl, scheduler)
        Mockito.`when`(matchRepositoryImpl.getUpcomingMatch("4328")).thenReturn(footballMatch)
        Mockito.`when`(leagueRepositoryImpl.getLeagueData("4328")).thenReturn(leagueList)
    }

    @Test
    fun getFootballMatchData() {
        mPresenter.getFootballMatchData()
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).displayFootballMatch(event)
        Mockito.verify(mView).hideLoading()
    }

    @Test
    fun getLeagueDetailData() {
        mPresenter.getLeagueDetailData()
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).showLeague(leagues)
        Mockito.verify(mView).hideLoading()
    }


}