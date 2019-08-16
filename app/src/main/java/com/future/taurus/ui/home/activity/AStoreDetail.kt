package com.future.taurus.ui.home.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.Toolbar
import com.lestin.yin.base.ABase
import kotlinx.android.synthetic.main.activity_astore_detail.*
import android.support.v4.content.ContextCompat
import android.widget.TextView
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.lestin.yin.Constants
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.ReviewInfo
import com.lestin.yin.entity.StoreDetailContent
import com.lestin.yin.utils.image.ShowImage
import com.lestin.yin.widget.listener.AppBarStateChangeListener
import kotlinx.android.synthetic.main.item_store_detail_bottom.*
import kotlinx.android.synthetic.main.item_store_detail_bottom.recylerView
import java.util.ArrayList


/**
 * 店铺详情页
 */
class AStoreDetail : ABase() {
    private var mAlertDialog: AlertDialog? = null
    private var mDialogWindow: Window? = null
    private var storeId : String = ""
    private var page : Int = 1


    val titleText = TextView(MyApplication.context)


    override fun layoutId(): Int = R.layout.activity_astore_detail

    override fun initView() {
        showProgressDialog()
        mImmersionBar!!.statusBarColor(R.color.transparent).init()

        storeId = intent.getStringExtra("id")
        toolbar.title = ""
        toolbar.setNavigationIcon(R.mipmap.store_detail_white_back)
        setSupportActionBar(toolbar)



    }

    override fun initData() {
        // 监听滚动状态
        app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {

            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if (state === State.EXPANDED) {
                    //展开状态
//                    toolbar.setNavigationIcon(R.drawable.my_yaoqing)
                    if (titleText != null)
                        titleText.visibility = View.GONE
                } else if (state === State.COLLAPSED) {
                    //折叠状态
//                    toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)
                    titleText.visibility = View.VISIBLE
                } else {
                    //中间状态
//                    toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)
                }
            }
        })

        getDateFromInterface()

        //点击返回键
        toolbar.setNavigationOnClickListener {
            finish()
        }
        tv_store_comment.setOnClickListener {
            //跳转到评论页
            var intent = Intent(MyApplication.context, AStoreComment::class.java)
            intent.putExtra("id",storeId)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.anim_botteom_silent)
        }
        //打电话
        iv_store_detail_call_phone.setOnClickListener {
            showCallPhoneDialog()
        }

    }
    //调取接口
    @SuppressLint("CheckResult")
    private fun getDateFromInterface() {
        mainModel.getStoreDetail(storeId, "1", "1",page.toString(),"10").subscribe { result ->
            if (Constants.SUCCESS.equals(result.code.toString())) {
                fillData(result.content)

            }
        }


    }
    //填充数据
    private fun fillData(content: StoreDetailContent) {
        setTitleCenter(toolbar,content.storeInfo.storeName)
        tv_store_detail_title.text = content.storeInfo.storeName
        ShowImage.show(this,content.storeInfo.storePics[0],mIv)

        tv_store_detail_tag.text = "#"+content.storeInfo.storeTag + "#"
        tv_store_detail_daka_number.text = content.storeInfo.visitedCount.toString()+"次打卡"
        tv_store_detail_location.text = content.storeInfo.storeName
        tv_store_detail_renjun.text = "人均：\$"+ content.storeInfo.avgPrice +"人"
        tv_store_detail_yinye.text = "营业中 | "+ content.storeInfo.storeHours
        tv_store_detail_distance.text = content.storeInfo.storeDistance.toString() + "米"

        setReviewList(content.reviewInfos)
        closeProgressDialog()
    }
    //设置攻略列表
    private fun setReviewList(reviewInfos: List<ReviewInfo>) {
        tv_store_detail_daren_number.text = "达人微攻略（"+reviewInfos.size+"）"

        recylerView.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }


        var list: MutableList<IHomeType> = ArrayList()
        list.addAll(reviewInfos)
        var homeAdapter = HomeAdapter(list)
        recylerView.adapter = homeAdapter
        recylerView.isFocusable = false

    }

    private fun showCallPhoneDialog() {
        //弹出Dialog
        val builder = AlertDialog.Builder(this, R.style.dialogNeed)
        mAlertDialog = builder.create()
        mAlertDialog!!.show()
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_store_detail_call_phone, null)
        val tvPhoneOne = dialogView.findViewById<TextView>(R.id.tv_call_phone_one)
        val tvPhoneTwo = dialogView.findViewById<TextView>(R.id.tv_call_phone_two)

        mAlertDialog!!.setContentView(dialogView)

        mDialogWindow = mAlertDialog!!.getWindow()
        mDialogWindow!!.setGravity(Gravity.CENTER)
        mDialogWindow!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        mAlertDialog!!.setCanceledOnTouchOutside(true)
        tvPhoneOne.setOnClickListener {
            callPhone(tvPhoneOne.text.toString())
        }
        tvPhoneTwo.setOnClickListener {
            callPhone(tvPhoneTwo.text.toString())
        }

    }

    private fun callPhone(tell: String) {
        /**
         * 拨打电话（跳转到拨号界面，用户手动点击拨打）
         *
         * @param phoneNum 电话号码
         */
        var intent = Intent(Intent.ACTION_DIAL)
        var data = Uri.parse("tel:$tell")
        intent.data = data
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun start() {

//        recylerView.layoutManager = GridLayoutManager(MyApplication.context, 3,
//                LinearLayoutManager.VERTICAL, false)

//        recylerView.layoutManager = LinearLayoutManager(this)

        recylerView.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }



        item_detail_container.isFillViewport = true
        item_detail_container.smoothScrollBy(0, 0)

    }

    //设置标题居中
    private fun setTitleCenter(toolbar: Toolbar,title : String) {
        titleText.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
//        titleText.setText(R.string.brvah_app_name)
        titleText.text = title
        titleText.textSize = 18f
        titleText.gravity = Gravity.CENTER
        titleText.visibility = View.GONE
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        titleText.layoutParams = layoutParams
        toolbar.addView(titleText)
    }


}
