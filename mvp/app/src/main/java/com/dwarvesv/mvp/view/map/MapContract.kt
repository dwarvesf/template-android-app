package com.dwarvesv.mvp.view.map

import com.dwarvesv.mvp.base.BasePresenter
import com.dwarvesv.mvp.base.BaseView

interface MapContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter

}
