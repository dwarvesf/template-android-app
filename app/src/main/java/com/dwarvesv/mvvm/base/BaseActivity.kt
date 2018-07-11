package com.dwarvesv.mvvm.base


import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast


open class BaseActivity : AppCompatActivity(), BaseFragment.Callback {

    override fun onFragmentAttached() = Unit

    override fun onFragmentDetached(tag: String) = Unit


    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    protected fun removeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
    }

    protected fun replaceFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    fun showToast(message: String?) {
        Toast.makeText(this, message ?: "", Toast.LENGTH_SHORT).show()
    }


    fun setToolbarTitle(title: String?) {
        supportActionBar!!.title = title
    }

    fun setDisplayHomeAsUpEnabled(enabled: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(enabled)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun setHomeButtonEnabled(enabled: Boolean) {
        supportActionBar!!.setHomeButtonEnabled(enabled)
    }
}
