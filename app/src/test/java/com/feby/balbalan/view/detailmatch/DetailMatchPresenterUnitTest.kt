package com.feby.balbalan.view.detailmatch

import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import com.feby.balbalan.model.Event
import com.feby.balbalan.model.FootballMatch
import com.feby.balbalan.model.Team
import com.feby.balbalan.model.Teams
import com.feby.balbalan.model.repository.LocalRepositoryImpl
import com.feby.balbalan.model.repository.TeamRepositoryImpl
import com.feby.balbalan.utils.SchedulerProvider
import com.feby.balbalan.utils.TestingSchedulerProvider

class DetailMatchPresenterUnitTest {

    @Mock
    lateinit var mView: DetailMatchContract.View

    @Mock
    lateinit var teamRepositoryImpl: TeamRepositoryImpl

    @Mock
    lateinit var localRepositoryImpl: LocalRepositoryImpl

    private lateinit var scheduler: SchedulerProvider

    private lateinit var match : FootballMatch

    private lateinit var mMatchPresenter: DetailMatchPresenter

    private val event = mutableListOf<Event>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestingSchedulerProvider()
        match = FootballMatch(event)
        mMatchPresenter = DetailMatchPresenter(mView, teamRepositoryImpl, localRepositoryImpl, scheduler)
    }

    @Test
    fun getBadgeHomeSuccess() {
        val id = "133637"
        val home = 1
        val list = mutableListOf<Team>().apply {
            add(Team())
        }
        val teamResponse = Teams(list)
        Mockito.`when`(teamRepositoryImpl.getTeamsDetail(id)).thenReturn(
            Flowable.just(teamResponse)
        )

        val inOrder = Mockito.inOrder(mView)
        mMatchPresenter.getBadge(id, home)
        inOrder.verify(mView).showBadgeImageHome(list[0].strTeamBadge ?: "")
    }

    @Test
    fun getBadgeAwaySuccess() {
        val id = "133604"
        val away = 2
        val list = mutableListOf<Team>().apply {
            add(Team())
        }
        val teamResponse = Teams(list)
        Mockito.`when`(teamRepositoryImpl.getTeamsDetail(id)).thenReturn(
            Flowable.just(teamResponse)
        )

        val inOrder = Mockito.inOrder(mView)
        mMatchPresenter.getBadge(id, away)
        inOrder.verify(mView).showBadgeImageAway(list[0].strTeamBadge ?: "")
    }

}