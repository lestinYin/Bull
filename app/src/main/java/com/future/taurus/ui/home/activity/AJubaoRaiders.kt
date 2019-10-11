package com.future.taurus.ui.home.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.lestin.yin.base.ABase
import com.lestin.yin.utils.image.ShowImage
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.activity_ajubao_raiders.*
import kotlinx.android.synthetic.main.activity_ajubao_raiders.toolbar
import kotlinx.android.synthetic.main.activity_ajubao_store.*
//举报攻略
class AJubaoRaiders : ABase() {
    val titleText = TextView(MyApplication.context)

    override fun layoutId(): Int = R.layout.activity_ajubao_raiders

    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.color_f3f3f3).init()
        toolbar.title = ""
        setSupportActionBar(toolbar)
        //无接口时
        setTitleCenter(toolbar,"举报攻略")
    }

    override fun initData() {
        rv_jubao.layoutManager = GridLayoutManager(MyApplication.context, 3,
                LinearLayoutManager.VERTICAL, false)
//
//        val reviewPics = reviewInfo.reviewPics
        val reviewPics = arrayOf("http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg", "http://f.hiphotos.baidu.com/image/h%3D300/sign=d985fb87d81b0ef473e89e5eedc551a1/b151f8198618367aa7f3cc7424738bd4b31ce525.jpg",
                "http://b.hiphotos.baidu.com/image/h%3D300/sign=92afee66fd36afc3110c39658318eb85/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg")
        var adapter = object : CommonAdapter<String>(MyApplication.context, R.layout.item_home_raiders_image, reviewPics.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                val view = holder!!.getView<ImageView>(R.id.iv_raiders_image)

                ShowImage.showRoundCorners(MyApplication.context,reviewPics[position],view,10)
            }
        }
        rv_jubao.adapter = adapter
    }

    override fun start() {
    }

    //设置标题居中
    private fun setTitleCenter(toolbar: Toolbar, title : String) {
        titleText.setTextColor(ContextCompat.getColor(applicationContext, R.color.color_333333))
//        titleText.setText(R.string.brvah_app_name)
        titleText.text = title
        titleText.textSize = 18f
        titleText.gravity = Gravity.CENTER
        titleText.visibility = View.VISIBLE
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        titleText.layoutParams = layoutParams
        toolbar.addView(titleText)
    }



}
