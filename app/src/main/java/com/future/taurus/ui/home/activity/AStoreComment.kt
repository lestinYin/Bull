package com.future.taurus.ui.home.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.*
import android.view.animation.TranslateAnimation
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.StoreCommentAdapter
import com.future.taurus.ui.home.entity.EHome
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_astore_comment.*
import kotlinx.android.synthetic.main.dialog_store_comment.*
import java.util.ArrayList

/**
 * 店铺评论页
 */
@SuppressLint("ResourceAsColor")
class AStoreComment : ABase(), DialogInterface.OnDismissListener {


    private var mAlertDialog: AlertDialog? = null
    private var mDialogWindow: Window? = null
    private var editComment: EditText? = null
    private var tvCommint: TextView? = null


    override fun initView() {
        mImmersionBar!!.statusBarColor(R.color.transparent).init()
        rv_store_comment.layoutManager = LinearLayoutManager(this)

    }

    override fun layoutId(): Int = R.layout.activity_astore_comment

    override fun initData() {
//        rl_store_comment_root.setBackgroundColor(R.color.transparent)

        var eHome = EHome(1, "")
        var eHome2 = EHome(1, "")
        var eHome6 = EHome(1, "")
        var eHome3 = EHome(2, "")
        var eHome4 = EHome(3, "")
        var eHome5 = EHome(3, "")
        var eHome7 = EHome(3, "")

        var list: MutableList<EHome> = ArrayList()
        list.add(eHome)
        list.add(eHome2)
        list.add(eHome6)
        list.add(eHome3)
        list.add(eHome4)
        list.add(eHome5)
        list.add(eHome7)

        var adapter = StoreCommentAdapter(list)
        rv_store_comment.adapter = adapter

    }

    override fun start() {
        rl_store_comment_root.setOnClickListener {
            onBackPressed()
        }
        rv_comment_bottome.setOnClickListener {
            showEditComment()
        }

    }
    //点击提交
    private fun duageComment() {
        val commentText = editComment!!.text.toString()
        if (TextUtils.isEmpty(commentText)) {
            ToastUtils.showShort("评论不能为空")
            return
        }
        mAlertDialog!!.dismiss()
    }

    //显示评论窗口
    private fun showEditComment() {

        //弹出Dialog
        val builder = AlertDialog.Builder(this, R.style.dialogNeed)
        mAlertDialog = builder.create()
        mAlertDialog!!.setOnDismissListener(this)
        mAlertDialog!!.show()
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_store_comment, null)
        editComment = dialogView.findViewById(R.id.et_input_comment)
        tvCommint = dialogView.findViewById(R.id.tv_commnet_commint)
        mAlertDialog!!.setContentView(dialogView)
        mDialogWindow = mAlertDialog!!.getWindow()
        if (mDialogWindow != null) {
            //解决无法弹出输入法的问题
            mDialogWindow!!.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        }
        mDialogWindow!!.setGravity(Gravity.BOTTOM)
        mDialogWindow!!.setWindowAnimations(R.style.BottomAnimation)
        mDialogWindow!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val params = mDialogWindow!!.attributes
        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE//显示dialog的时候,就显示软键盘
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND//就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
//        window.setWindowAnimations(R.style.dialogAnim);
        mDialogWindow!!.attributes = params
        mAlertDialog!!.setCanceledOnTouchOutside(true)
        tvCommint!!.setOnClickListener {
            duageComment()
        }
    }

    override fun onDismiss(p0: DialogInterface?) {
//        closeKeyBord(editComment!!,this)
        //显示软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_botteom_silent, R.anim.fade_out)
    }


}
