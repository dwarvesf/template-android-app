package com.dwarvesv.mvvm.view.home

class HomePresenter(val view: HomeContract.View) : HomeContract.Presenter {

    init {
        view.presenter = this
    }


}
