package com.farmand.foodapp.ui.detail

import com.farmand.foodapp.data.database.FoodEntity
import com.farmand.foodapp.data.repository.DetailRepository
import com.farmand.foodapp.utils.applyIoScheduler
import com.ramand.noteapp.utils.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val repository: DetailRepository, private val view: DetailContracts.View) :
    DetailContracts.Presenter, BasePresenterImpl() {
    override fun callDetailApi(id: Int) {
        if (view.checkInternet()){
            view.showLoading()
            disposable = repository.loadFoodDetail(id)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when(response.code()){
                        in 200 .. 202 ->{
                            response.body()?.let {
                                if (it.meals != null) {
                                    if (it.meals.isNotEmpty()) {
                                        view.loadDetail(it)
                                    }
                                }
                            }
                        }
                        422 ->{}
                    }
                },{
                    view.hideLoading()
                    view.serverError(it.message.toString())
                })
        }else{
            view.internetError(false)
        }

    }

    override fun saveFood(entity: FoodEntity) {
        disposable = repository.saveFood(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.updateFavorite(true)
            }
    }

    override fun deleteFood(entity: FoodEntity) {
        disposable = repository.deleteFood(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.updateFavorite(false)
            }
    }

    override fun checkFavorite(id: Int) {
        disposable = repository.existsFood(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.updateFavorite(it)
            }
    }
}