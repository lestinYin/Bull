package com.future.taurus.ui.home.activity

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
import com.future.taurus.ui.home.entity.EHome
import com.future.taurus.ui.home.entity.LHome
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


    val titleText = TextView(MyApplication.context)


    override fun layoutId(): Int = R.layout.activity_astore_detail

    override fun initView() {
        mImmersionBar!!.statusBarColor(R.color.transparent).init()

        toolbar.title = ""
        toolbar.setNavigationIcon(R.mipmap.store_detail_white_back)
        setSupportActionBar(toolbar)
        setTitleCenter(toolbar)

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
        ShowImage.showCircle(MyApplication.context, "https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png", iv_head1)
        ShowImage.showCircle(MyApplication.context, "https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png", iv_head2)
        ShowImage.showCircle(MyApplication.context, "https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png", iv_head3)
        ShowImage.showCircle(MyApplication.context, "https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png", iv_head4)
        //点击返回键
        toolbar.setNavigationOnClickListener {
            finish()
        }
        tv_store_comment.setOnClickListener {
            //跳转到评论页
            var intent = Intent(MyApplication.context, AStoreComment::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.anim_botteom_silent)
        }
        //打电话
        iv_store_detail_call_phone.setOnClickListener {
            showCallPhoneDialog()
        }

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
//
//        var adapter = object : CommonAdapter<String>(MyApplication.context, R.layout.item_home_raiders, imageList.toMutableList()) {
//            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
//                val view = holder!!.getView<ImageView>(R.id.iv_raiders_head)
//
//                ShowImage.showCircle(MyApplication.context,imageList[position],view)
//
//
//            }
//        }
//        recylerView.adapter = adapter

        var eHome = EHome(3, "")
        var eHome2 = EHome(3, "")
        var eHome6 = EHome(3, "")
        var eHome3 = EHome(3, "")
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
        var homes: LHome.Home = LHome.Home()
        homes.setList(list)
        var homeAdapter = HomeAdapter(list)

        homeAdapter.setData(list)

        recylerView.adapter = homeAdapter
        recylerView.isFocusable = false

        item_detail_container.isFillViewport = true
        item_detail_container.smoothScrollBy(0, 0)

    }

    //设置标题居中
    private fun setTitleCenter(toolbar: Toolbar) {
        titleText.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
//        titleText.setText(R.string.brvah_app_name)
        titleText.text = "好乐迪KTV"
        titleText.textSize = 18f
        titleText.gravity = Gravity.CENTER
        titleText.visibility = View.GONE
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        titleText.layoutParams = layoutParams
        toolbar.addView(titleText)
    }


}
