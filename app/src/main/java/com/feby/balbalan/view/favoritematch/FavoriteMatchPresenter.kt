package com.feby.balbalan.view.favoritematch

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import com.feby.balbalan.model.Event
import com.feby.balbalan.model.FootballMatch
import com.feby.balbalan.model.repository.LocalRepositoryImpl
import com.feby.balbalan.model.repository.MatchRepositoryImpl
import com.feby.balbalan.utils.SchedulerProvider
import java.util.*

class FavoriteMatchPresenter(val mView: FavoriteMatchContract.View,
                             private val matchRepositoryImpl: MatchRepositoryImpl,
                             private val localRepositoryImpl: LocalRepositoryImpl,
                             private val scheduler: SchedulerProvider) : FavoriteMatchContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData() {
        mView.showLoading()
        val favList = localRepositoryImpl.getMatchFromDb()
        val eventList: MutableList<Event> = mutableListOf()
        for (fav in favList){
            compositeDisposable.add(matchRepositoryImpl.getEventById(fav.idEvent)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<FootballMatch>(){
                    override fun onComplete() {
                        mView.hideLoading()
                        mView.hideSwipeRefresh()
                    }

                    override fun onNext(t: FootballMatch) {
                        eventList.add(t.events[0])
                        mView.displayFootballMatch(eventList)
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayFootballMatch(Collections.emptyList())
                        mView.hideLoading()
                        mView.hideSwipeRefresh()
                    }

                })
            )
        }

        if(favList.isEmpty()){
            mView.hideLoading()
            mView.hideSwipeRefresh()
            mView.displayFootballMatch(eventList)
        }
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}