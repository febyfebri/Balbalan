package com.feby.balbalan.view.detailmatch



import io.reactivex.disposables.CompositeDisposable
import com.feby.balbalan.model.repository.TeamRepositoryImpl
import com.feby.balbalan.model.repository.LocalRepositoryImpl
import com.feby.balbalan.utils.SchedulerProvider


class DetailMatchPresenter(
    private val mView: DetailMatchContract.View, private val teamRepositoryImpl: TeamRepositoryImpl,
    private val localRepositoryImpl: LocalRepositoryImpl,
    private val scheduler: SchedulerProvider
) : DetailMatchContract.Presenter {

    private val home = 1

    override fun deleteMatch(id: String) {
        localRepositoryImpl.deleteData(id)
    }

    override fun checkMatch(id: String) {
        mView.setFavoriteState(localRepositoryImpl.checkFavorite(id))
    }

    override fun insertMatch(eventId: String, homeId: String, awayId: String) {
        localRepositoryImpl.insertData(eventId, homeId, awayId)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getBadge(id: String, team: Int) {
        compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(id)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({ t ->
                t?.teams?.let {
                    if (team == home)
                        mView.showBadgeImageHome(it[0].strTeamBadge ?: "")
                    else
                        mView.showBadgeImageAway(it[0].strTeamBadge ?: "")
                }
            }, {

            }))
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}