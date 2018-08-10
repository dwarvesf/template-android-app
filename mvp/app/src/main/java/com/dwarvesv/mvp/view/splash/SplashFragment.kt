package {{packageName}}.view.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.OnCompositionLoadedListener
import {{packageName}}.R
import {{packageName}}.base.BaseFragment
import {{packageName}}.utils.checkEnableLogin
import {{packageName}}.utils.disposebag.DisposeBag
import {{packageName}}.utils.disposebag.disposedBy


import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : BaseFragment(), OnCompositionLoadedListener {


    private var listener: InteractionListener? = null

    private val bag = DisposeBag(this)
    internal var mAnimFiles = arrayOf("empty_status.json", "letter_b_monster.json", "permission.json", "ice_cream_animation.json")
    internal var mCurrentAnim = 0
    private var animFileName: String = ""

    companion object {
        fun getInstance() = SplashFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement Fragment.InteractionListener")
        }
    }

    override fun setUpView(view: View, savedInstanceState: Bundle?) {

        RxView.clicks(btnNext)
                .subscribe { _ ->
                    if (checkEnableLogin(animFileName))
                        listener?.navigateToLogin()
                    else {
                        btnNext.setEnabled(false)
                        changeAnim()
                    }
                }
                .disposedBy(bag)

    }

    private fun changeAnim() {
        Handler().post {
            animFileName = mAnimFiles[++mCurrentAnim % mAnimFiles.size]
            enableLogin(checkEnableLogin(animFileName))
            LottieComposition.Factory.fromAssetFileName(context, animFileName, this)
        }
    }

    private fun enableLogin(enable: Boolean) {
        if (enable)
            btnNext.setText(getString(R.string.login))
    }

    override fun onCompositionLoaded(composition: LottieComposition?) {
        composition?.let { animView.setComposition(it) }
        animView.playAnimation()
        btnNext.setEnabled(true)
    }

    interface InteractionListener {

        fun navigateToLogin()
    }
}