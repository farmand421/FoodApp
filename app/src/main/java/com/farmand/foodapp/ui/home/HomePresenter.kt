package com.farmand.foodapp.ui.home

import com.farmand.foodapp.data.repository.HomeRepository
import com.farmand.foodapp.utils.applyIoScheduler
import com.ramand.noteapp.utils.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(private val repository: HomeRepository , private val view: HomeContracts.View) :
HomeContracts.Presenter,BasePresenterImpl(){
    override fun callFoodRandom() {
        if (view.checkInternet()){
            disposable = repository.loadFoodRandom()
                .applyIoScheduler()
                .subscribe({ response ->

                    when(response.code()){
                        in 200 .. 202 ->{
                            response.body()?.let {
                                view.loadFoodRandom(it)
                            }
                        }
                        422 ->{}
                    }
                },{
                    view.serverError(it.message.toString())
                })
        }else{
            view.internetError(false)
        }

    }

    override fun callCategoriesList() {
        if (view.checkInternet()){
            disposable = repository.loadCategoriesList()
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when(response.code()){
                        in 200 .. 202 ->{
                            response.body()?.let {
                                if (it.categories.isNotEmpty()) {
                                    view.loadCategoriesList(it)
                                }
                            }
                        }
                        422 ->{}
                        in 400 .. 499 ->{}
                        in 500 .. 599 ->{}
                    }
                },{
                    view.hideLoading()
                    view.serverError(it.message.toString())
                })
        }else{
            view.internetError(false)
        }

    }

    override fun callFoodsList(letter: String) {
        if (view.checkInternet()){
            view.foodsLoadingState(true)
            disposable = repository.loadFoodsList(letter)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.foodsLoadingState(false)
                    when(response.code()){
                        in 200 .. 202 ->{
                            response.body()?.let {
                                if (it.meals!!.isNotEmpty()) {
                                    view.loadFoodsList(it)
                                }
                            }
                        }
                        422 ->{}
                        in 400 .. 499 ->{}
                        in 500 .. 599 ->{}
                    }
                },{
                    view.foodsLoadingState(false)
                    view.serverError(it.message.toString())
                })
        }else{
            view.internetError(false)
        }

    }

    override fun callSearchFood(letter: String) {
        if (view.checkInternet()) {
            view.foodsLoadingState(true)
            disposable = repository.loadSearchFood(letter)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.foodsLoadingState(false)
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                if (itBody.meals != null) {
                                    if (itBody.meals.isNotEmpty()) {
                                        view.loadFoodsList(itBody)
                                    }
                                } else {
                                    view.emptyList()
                                }
                            }
                        }
                    }

                }, {
                    view.foodsLoadingState(false)
                    view.serverError(it.message.toString())
                })
        } else {
            view.internetError(false)
        }
    }

    override fun callFoodsByCategory(letter: String) {
        if (view.checkInternet()){
            view.foodsLoadingState(true)
            disposable = repository.loadFoodsByCategory(letter)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.foodsLoadingState(false)
                    when(response.code()){
                        in 200 .. 202 ->{
                            response.body()?.let {
                                if (it.meals!!.isNotEmpty()) {
                                    view.loadFoodsList(it)
                                }
                            }
                        }
                        422 ->{}
                        in 400 .. 499 ->{}
                        in 500 .. 599 ->{}
                    }
                },{
                    view.foodsLoadingState(false)
                    view.serverError(it.message.toString())
                })
        }else{
            view.internetError(false)
        }
    }
}