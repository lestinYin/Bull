package com.future.taurus.ui.home.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.lestin.yin.base.ABase
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.ReviewInfo
import com.lestin.yin.utils.image.ShowImage
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.activity_adaren_home.*
import kotlinx.android.synthetic.main.activity_adaren_home.mIv
import kotlinx.android.synthetic.main.activity_adaren_home.toolbar
import kotlinx.android.synthetic.main.activity_astore_detail.*
import kotlinx.android.synthetic.main.include_daren_bottom.*
import java.util.ArrayList

class ADarenHome : ABase() {
    val titleText = TextView(MyApplication.context)

    override fun layoutId(): Int = R.layout.activity_adaren_home

    override fun initView() {
        mImmersionBar!!.statusBarColor(R.color.transparent).init()
//        ShowImage.show(this,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569412934394&di=580ce8a0585eb4af3c29cffe7e1e7ab1&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D2275054218%2C1241459537%26fm%3D214%26gp%3D0.jpg",mIv)


        toolbar.title = ""
        toolbar.setNavigationIcon(R.mipmap.white_arrow_back)
        setSupportActionBar(toolbar)
        setTitleCenter(toolbar, "好迪KTV")
    }

    override fun initData() {

        var list = arrayListOf<ReviewInfo>()
        list.add(ReviewInfo("", "", "", "", "", "", "", "", "", 8, 5, emptyList(), "", "", 5))
        list.add(ReviewInfo("", "", "", "", "", "", "", "", "", 8, 5, emptyList(), "", "", 5))
        list.add(ReviewInfo("", "", "", "", "", "", "", "", "", 8, 5, emptyList(), "", "", 5))
        list.add(ReviewInfo("", "", "", "", "", "", "", "", "", 8, 5, emptyList(), "", "", 5))
        setReviewList(list)
    }

    override fun start() {
        iv_jubao_daren.setOnClickListener {

            startActivity(Intent(this,AJubaoDaren::class.java))
        }
    }

    //设置标题居中
    private fun setTitleCenter(toolbar: Toolbar, title: String) {
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

    //设置攻略列表
    private fun setReviewList(reviewInfos: List<ReviewInfo>) {

        recylerView.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val reviewPics = arrayOf("http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg", "http://f.hiphotos.baidu.com/image/h%3D300/sign=d985fb87d81b0ef473e89e5eedc551a1/b151f8198618367aa7f3cc7424738bd4b31ce525.jpg",
                "http://b.hiphotos.baidu.com/image/h%3D300/sign=92afee66fd36afc3110c39658318eb85/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg")
        var adapter = object : CommonAdapter<String>(MyApplication.context, R.layout.item_daren_list, reviewPics.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                val rvPic = holder!!.getView<RecyclerView>(R.id.rv_daren_list)

                rvPic.layoutManager = GridLayoutManager(MyApplication.context, 3,
                        LinearLayoutManager.VERTICAL, false)

                val reviewPics = arrayOf("http://b.hiphotos.baidu.com/image/h%3D300/sign=ad628627aacc7cd9e52d32d909032104/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg", "http://f.hiphotos.baidu.com/image/h%3D300/sign=d985fb87d81b0ef473e89e5eedc551a1/b151f8198618367aa7f3cc7424738bd4b31ce525.jpg",
                        "http://b.hiphotos.baidu.com/image/h%3D300/sign=92afee66fd36afc3110c39658318eb85/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg")
                var adapter = object : CommonAdapter<String>(MyApplication.context, R.layout.item_home_raiders_image, reviewPics.toMutableList()) {
                    override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                        val view = holder!!.getView<ImageView>(R.id.iv_raiders_image)

                        ShowImage.showRoundCorners(MyApplication.context, reviewPics[position], view, 10)
                    }
                }
                rvPic.adapter = adapter
            }
        }
        recylerView.adapter = adapter

    }

}
