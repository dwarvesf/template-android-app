package {{packageName}}.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import {{packageName}}.service.UserApi
import {{packageName}}.service.UserService

abstract class BaseFragment : Fragment() {

    private var mActivity: BaseActivity? = null
    lateinit var userApi: UserApi

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        userApi = UserService.getInstance().api
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view, savedInstanceState)
    }

    protected abstract fun setUpView(view: View, savedInstanceState: Bundle?)

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

}
