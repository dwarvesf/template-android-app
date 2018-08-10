package {{packageName}}.view.detail

import android.os.Bundle
import {{packageName}}.R
import {{packageName}}.base.BaseNoAppBarActivity

class DetailActivity : BaseNoAppBarActivity(), DetailFragment.InteractionListener {

    private lateinit var detailFragment: DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            detailFragment = DetailFragment.newInstance()

            if (intent != null) {
                detailFragment.arguments = intent.extras
            }

            replaceFragment(R.id.fragmentContainer, detailFragment)
        }

    }

}
