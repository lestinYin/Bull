package com.future.taurus.ui.home.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.TextView
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.StoreCommentAdapter
import com.lestin.yin.Constants
import com.lestin.yin.base.ABase
import com.lestin.yin.entity.StoreComentListContent
import com.lestin.yin.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_astore_comment.*

/**
 * 店铺评论页
 */
@SuppressLint("ResourceAsColor")
class AStoreComment : ABase(), DialogInterface.OnDismissListener {


    private var mAlertDialog: AlertDialog? = null
    private var mDialogWindow: Window? = null
    private var editComment: EditText? = null
    private var tvCommint: TextView? = null
    private var page : Int = 1
    private var storeId : String = ""


    override fun initView() {
        mImmersionBar!!.statusBarColor(R.color.transparent).init()
        storeId = intent.getStringExtra("id")
        rv_store_comment.layoutManager = LinearLayoutManager(this)

    }

    override fun layoutId(): Int = R.layout.activity_astore_comment

    override fun initData() {
        getStoreCommentList()

    }
    //获取评论列表
    @SuppressLint("CheckResult")
    private fun getStoreCommentList() {
        mainModel.getStoreComentList(storeId, page.toString(),"10").subscribe { result ->
            if (Constants.SUCCESS.equals(result.code.toString())) {
                fillData(result.content)

            }
        }

    }
    //填充数据
    private fun fillData(content: StoreComentListContent) {
        tv_store_comment_number.text  = content.storeComments.size.toString() + "  评论"
        var adapter = StoreCommentAdapter(content.storeComments)
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
        commitComment(commentText)

    }
    //提交评论
    @SuppressLint("CheckResult")
    private fun commitComment(commentText: String) {
        mainModel.commitStoreComment(storeId, commentText).subscribe { result ->
            if (Constants.SUCCESS.equals(result.code.toString())) {
                getStoreCommentList()
                ToastUtils.showShort("评论成功")
                mAlertDialog!!.dismiss()
            } else {
                ToastUtils.showShort("评论失败 请重试")
            }
        }

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
        //关闭软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_botteom_silent, R.anim.fade_out)
    }


}
