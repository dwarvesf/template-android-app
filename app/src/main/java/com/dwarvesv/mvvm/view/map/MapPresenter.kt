package com.dwarvesv.mvvm.view.map

class MapPresenter(val view: MapContract.View) : MapContract.Presenter {

    init {
        view.presenter = this
    }


}
