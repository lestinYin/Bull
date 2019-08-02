package com.future.taurus.ui.home.provider

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.future.taurus.MyApplication
import com.future.taurus.R
import com.future.taurus.ui.home.adapter.HomeAdapter
import com.future.taurus.ui.home.entity.EHome
import com.lestin.yin.utils.image.ShowImage
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.future.taurus.ui.home
 * @ClassName:      StoreProvider
 * @Description:     攻略布局
 * @Author:         lestin.yin
 * @CreateDate:     2019-07-30 15:31
 * @Version:        1.0
 */
class RaidersProvider : BaseItemProvider<EHome, BaseViewHolder>() {
    val imageList = arrayOf("https://tse1-mm.cn.bing.net/th?id=OET.1ec0d4ee50f84be8a26861b31fc065e6&w=272&h=135&c=7&rs=1&o=5&pid=1.9",
            "https://tse1-mm.cn.bing.net/th?id=OET.c1416d1878544dd3a8b38fce6d9a9121&w=272&h=272&c=7&rs=1&o=5&pid=1.9",
            "https://tse1-mm.cn.bing.net/th?id=OET.32d6002da07b470e814a7d2412e9692a&w=272&h=135&c=7&rs=1&o=5&pid=1.9",
            "https://tse1-mm.cn.bing.net/th?id=OET.9cf494b892eb4cfe807968f5a4af7b55&w=135&h=272&c=7&rs=1&o=5&pid=1.9")
    override fun layout(): Int = R.layout.item_home_raiders

    override fun viewType(): Int = HomeAdapter.TYPE_TEXT_IMG

    override fun convert(helper: BaseViewHolder?, data: EHome?, position: Int) {
        helper!!.setText(R.id.tv_raiders_name,"Sandy小花猫咪")

        val head4 = helper.getView<ImageView>(R.id.iv_raiders_head)
        ShowImage.showCircle(MyApplication.context,"https://upload.jianshu.io/users/upload_avatars/3333716/14924725-5e8f-4e3c-b77e-20a80f9b2ee3.png",head4)

        val rvPic = helper.getView<RecyclerView>(R.id.rv_home_raiders_img)

        rvPic.layoutManager = GridLayoutManager(MyApplication.context, 3,
                LinearLayoutManager.VERTICAL, false)


        var adapter = object : CommonAdapter<String>(MyApplication.context, R.layout.item_home_raiders_image, imageList.toMutableList()) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                val view = holder!!.getView<ImageView>(R.id.iv_raiders_image)

                ShowImage.showRoundCorners(MyApplication.context,imageList[position],view,5)


            }
        }
        rvPic.adapter = adapter
    }
}