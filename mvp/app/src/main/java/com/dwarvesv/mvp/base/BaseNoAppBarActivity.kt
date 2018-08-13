package {{packageName}}.base

import android.os.Bundle
import {{packageName}}.R


open class BaseNoAppBarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_appbar_layout)
    }
}