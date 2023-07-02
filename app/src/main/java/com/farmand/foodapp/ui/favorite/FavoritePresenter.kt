package com.farmand.foodapp.ui.favorite

import com.farmand.foodapp.data.repository.FavoriteRepository
import com.ramand.noteapp.utils.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FavoritePresenter @Inject constructor(private val repository: FavoriteRepository, private val view: FavoriteContracts.View) :
    FavoriteContracts.Presenter, BasePresenterImpl() {
    override fun loadAllFoods() {
        disposable = repository.loadAllFoods()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.showAllFoods(it)
            }
    }
}