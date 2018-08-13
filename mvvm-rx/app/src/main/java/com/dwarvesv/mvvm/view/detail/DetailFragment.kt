package {{packageName}}.view.detail

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import {{packageName}}.R
import {{packageName}}.base.BaseFragment
import {{packageName}}.data.model.User
import {{packageName}}.utils.Keys
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment() {

    private var listener: InteractionListener? = null
    private lateinit var viewModel: DetailViewModel
    companion object {

        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement Fragment.InteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun setUpView(view: View, savedInstanceState: Bundle?) {

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        viewModel = DetailViewModel()
        val args = arguments
        if (args != null) {
            val user: User = args.getParcelable(Keys.BundleKeys.BUNDLE_PARCELABLE_KEY_DATAMVP) as User
            setData(user)
        }
    }

    private fun setData(user: User) {
        (activity as AppCompatActivity).setTitle(user.name)
    }

    interface InteractionListener

}
