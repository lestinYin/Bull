package com.xiaozhu.pinche.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.xiaozhu.pinche.Constants
import com.xiaozhu.pinche.MyApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * @name Pinche
 * @class name：com.xiaozhu.pinche.base
 * @class describe
 * @author lestin.yin yinmaolin8@gmail.com
 * @time 2018/8/22 下午4:40
 * @change
 * @chang time
 * @class describe
 */
abstract class FBase : Fragment() {

    private var compositeDisposable = CompositeDisposable()

    private var mDialogUtil: DialogUtil? = null

    var spManager : SPManager = SPManager(MyApplication.context)
    var mUser : EUser? = null
    protected var mBinder: Unbinder? = null

    val mainModel: MainModel by lazy {
        MainModel()
    }
    /**
     * 多种状态的 View 的切换
     */
//    protected var mLayoutStatusView: MultipleStatusView? = null
    var mImmersionBar: ImmersionBar? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutId(),null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUser =  spManager.get(Constants.USER_INFO, EUser::class.java)

        initView()
        initData()
        start()
        initListener()


    }

    private fun initListener() {
//        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

//    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
//        start()
//    }


    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()


    /**
     * 打卡软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }


    override fun onDestroy() {
        super.onDestroy()
//        MyApplication.getRefWatcher(this)?.watch(this)

        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }

        if (mImmersionBar != null)
            mImmersionBar!!.destroy() //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态

        if (mBinder != null) {
            mBinder!!.unbind()
        }
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


    protected fun showProgressDialog() {
        if (isShowRequestProgress()) {
            mDialogUtil = getProgressDialog()
            if (mDialogUtil != null) {
                mDialogUtil!!.showProgressDialog()
            }
        }
    }

    protected fun isShowProgressDialog(): Boolean {
        var showing = false
        if (mDialogUtil != null) {
            showing = mDialogUtil!!.isDialogShowing()
        }
        return showing
    }

    protected fun closeProgressDialog() {
        if (isShowRequestProgress()) {
            if (mDialogUtil != null) {
                mDialogUtil!!.closeProgressDialog()
            }
        }
    }

    internal fun getProgressDialog(): DialogUtil {
        if (mDialogUtil == null) {
            mDialogUtil = DialogUtil()
            mDialogUtil!!.createProgressDialog(context as Activity?)
        }
        return mDialogUtil as DialogUtil
    }

    /**
     * 是否显示请求的加载对话框，默认显示。即在任何有网络请求的地方，在请求发送之前显示正在加载，并在加载完成后隐藏
     */
    protected fun isShowRequestProgress(): Boolean {
        return true
    }

    /**
     * 初始化沉浸式
     */
    protected fun initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar!!.statusBarColor(R.color.colorPrimary)
        mImmersionBar!!.init()
    }
}