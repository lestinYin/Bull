package com.jinniu.my


import com.lestin.yin.base.ABase
import com.lestin.yin.utils.image.ShowImage
import kotlinx.android.synthetic.main.activity_amy.*
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import android.widget.TextView
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import android.support.v7.widget.LinearLayoutManager






class AMy : ABase() {
    val info = arrayOf("汇兑信息","安全电话","应急卡","小费助手","电话充值","帮助中心","积分兑换","推广收益")
    val infoImg = arrayOf(R.mipmap.my_huidui,R.mipmap.my_tool_phone,R.mipmap.my_tool_yingjika,R.mipmap.my_tool_xiaofei,R.mipmap.my_tool_chongzhi,R.mipmap.my_tool_help,R.mipmap.my_tool_jifen,R.mipmap.my_tool_tuiguang)

    override fun layoutId(): Int {


        return R.layout.activity_amy

    }
    override fun initView() {
        mImmersionBar!!.statusBarDarkFont(true).statusBarColor(R.color.transparent).init()
        //设置布局管理器
        rv_my.layoutManager = GridLayoutManager(this, 4,
                LinearLayoutManager.VERTICAL, false)
    }

    override fun initData() {
        ShowImage.showCircle(applicationContext,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",iv_head_img)
        rl_card.translationZ= 20F
    }

    override fun start() {
        setTools()
    }

    /**
     * 设置工具集
     */
    private fun setTools() {
        var adapter = object : CommonAdapter<String>(this, R.layout.item_my_tools, info.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                holder!!.getView<ImageView>(R.id.iv_item_my).background = getDrawable(infoImg[position])
                holder!!.getView<TextView>(R.id.tv_item_my).text = info[position]

                

            }
        }
        rv_my.adapter = adapter
    }

}
