package {{packageName}}.view.map

import {{packageName}}.base.BasePresenter
import {{packageName}}.base.BaseView

interface MapContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter

}
