package com.jinniu.delivery.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.jinniu.delivery.adapter.StoreListAdapter
import com.lestin.yin.base.FBase
import com.lestin.yin.entity.IHomeType
import com.lestin.yin.entity.Line
import com.lestin.yin.utils.image.ShowImage
import kotlinx.android.synthetic.main.fragment_delivery_home.recylerView
import kotlinx.android.synthetic.main.fragment_store_list.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jinniu.delivery.R
import com.jinniu.delivery.activity.ADeliveryStoreDetail


/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.future.taurus.ui.home.fragment
 * @ClassName:      FHome
 * @Author:         Lestin.Yin
 * @CreateDate:     2019/7/25 11:49
 * @Description:     首页展示
 * @Version:        1.0
 */
class FStoreList : FBase() {
//    var mLLCategory : LinearLayout? = null

    override fun layoutId(): Int = R.layout.fragment_store_list

    private var page: Int = 1

    override fun initData() {
        var deliveryList: MutableList<IHomeType> = ArrayList()
        var line = Line("1")
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)
        deliveryList.add(line)

        var homeAdapter = StoreListAdapter(deliveryList)
        recylerView.adapter = homeAdapter

        homeAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            var  intent = Intent(context, ADeliveryStoreDetail::class.java)
            startActivity(intent)
        }





    }





    override fun initView() {
//        mLLCategory = ll_categorys

        recylerView.layoutManager = LinearLayoutManager(context)

//        recylerView.isNestedScrollingEnabled = false//禁止滑动



    }

    override fun start() {
        setHorizentalCategory()
    }

    fun setHorizentalCategory() {
        val categoryList = arrayOf("饺子混沌 包子粥", "快餐便当", "汉堡薯条", "意面披萨", "包子粥店")
        //为布局中textview设置好相关属性
        for (i in categoryList) {
            val categoryView = LayoutInflater.from(context).inflate(R.layout.item_storelist_category, null)
            categoryView.findViewById<TextView>(R.id.tv_store_list_category_name).text = i
            val catePic = categoryView.findViewById<ImageView>(R.id.iv_store_list_category_pic)
            ShowImage.showCircle(context,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566210613338&di=6dd642ad8802e95cc8779406941d8ee5&imgtype=0&src=http%3A%2F%2Fpic67.nipic.com%2Ffile%2F20150514%2F3269097_162102734000_2.jpg",catePic)
            ll_categorys.addView(categoryView)
//            ll_categorys.invalidate()
        }
    }


    private val PARAM_STATUS = "status"

    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FStoreList {
            val fragment = FStoreList()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }
}