package com.dwarvesv.mvvm.view.detail

class DetailPresenter(val view: DetailContract.View) : DetailContract.Presenter {

    init {
        view.presenter = this
    }


}
