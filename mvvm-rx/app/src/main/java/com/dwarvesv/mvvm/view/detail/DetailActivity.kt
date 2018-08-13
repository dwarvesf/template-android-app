package {{packageName}}.view.detail

import android.os.Bundle
import {{packageName}}.R
import {{packageName}}.base.BaseNoAppBarActivity

class DetailActivity : BaseNoAppBarActivity(), DetailFragment.InteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setFragment(DetailFragment.newInstance(), intent.extras)
        }
    }

    private fun setFragment(detailFragment: DetailFragment, extras: Bundle) {

        detailFragment.arguments = extras
        replaceFragment(R.id.fragmentContainer, detailFragment)
    }
}
