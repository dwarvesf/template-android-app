package com.dwarvesv.mvp.view.detail

import com.dwarvesv.mvp.base.BasePresenter
import com.dwarvesv.mvp.base.BaseView

interface DetailContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter

}
