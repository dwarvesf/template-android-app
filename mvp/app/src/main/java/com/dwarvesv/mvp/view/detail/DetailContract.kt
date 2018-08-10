package {{packageName}}.view.detail

import {{packageName}}.base.BasePresenter
import {{packageName}}.base.BaseView

interface DetailContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter

}
