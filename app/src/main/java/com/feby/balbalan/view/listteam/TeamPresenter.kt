package com.feby.balbalan.view.listteam

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import com.feby.balbalan.model.Teams
import com.feby.balbalan.model.repository.TeamRepositoryImpl
import com.feby.balbalan.utils.SchedulerProvider
import java.util.*

class TeamPresenter(val mView : TeamContract.View, private val teamRepositoryImpl: TeamRepositoryImpl,
                    val scheduler: SchedulerProvider
): TeamContract.Presenter {

    private val compositeDisposable = CompositeDisposable()
    override fun getTeamData(leagueName: String) {
        mView.showLoading()
        compositeDisposable.add(teamRepositoryImpl.getAllTeam(leagueName)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object: ResourceSubscriber<Teams>(){
                override fun onComplete() {
                    mView.hideLoading()
                }

                override fun onNext(t: Teams) {
                    mView.displayTeams(t.teams)
                }

                override fun onError(t: Throwable?) {
                    mView.displayTeams(Collections.emptyList())
                    mView.hideLoading()
                }

            })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

}