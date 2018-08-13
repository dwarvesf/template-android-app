package {{packageName}}.view.list

import android.os.Bundle
import {{packageName}}.R
import {{packageName}}.base.BaseNoAppBarActivity
import {{packageName}}.utils.Keys.BundleKeys.BUNDLE_PARCELABLE_KEY_DATAMVP
import {{packageName}}.view.detail.DetailActivity
import org.jetbrains.anko.intentFor


class ListActivity : BaseNoAppBarActivity(), ListFragment.InteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setFragment(ListFragment.newInstance())
        }

        setToolbarTitle(getString(R.string.activity_title_list))
        setDisplayHomeAsUpEnabled(true)
    }

    private fun setFragment(listFragment: ListFragment) {
        replaceFragment(R.id.fragmentContainer, listFragment)
    }


    override fun navigateToDetail(user: {{packageName}}.data.model.User) {
        startActivity(intentFor<DetailActivity>(BUNDLE_PARCELABLE_KEY_DATAMVP to user))
    }

}
