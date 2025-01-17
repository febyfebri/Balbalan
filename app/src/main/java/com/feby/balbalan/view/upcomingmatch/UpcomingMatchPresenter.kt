package com.feby.balbalan.view.upcomingmatch

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import com.feby.balbalan.model.FootballMatch
import com.feby.balbalan.model.Leagues
import com.feby.balbalan.model.repository.LeagueRepositoryImpl
import com.feby.balbalan.model.repository.MatchRepositoryImpl
import com.feby.balbalan.utils.SchedulerProvider
import java.util.*


class UpcomingMatchPresenter(val mView :  MatchContract.View,
                             private val matchRepositoryImpl: MatchRepositoryImpl,
                             private val leagueRepositoryImpl: LeagueRepositoryImpl,
                             private val scheduler: SchedulerProvider
) : MatchContract.Presenter {


    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData(leagueName: String) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.getUpcomingMatch(leagueName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<FootballMatch>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: FootballMatch) {
                        mView.displayFootballMatch(t.events)
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        mView.displayFootballMatch(Collections.emptyList())
                    }

                })
                )
    }

    override fun getLeagueDetailData(leagueId: String) {
        mView.showLoading()
        compositeDisposable.add(leagueRepositoryImpl.getLeagueData(leagueId)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object : ResourceSubscriber<Leagues>(){
                override fun onComplete() {
                    mView.hideLoading()
                }

                override fun onNext(t: Leagues) {
                    mView.showLeague(t.leagues)
                }

                override fun onError(e: Throwable) {
                    mView.hideLoading()
                    mView.showLeague(Collections.emptyList())
                }

            })
        )
    }

}