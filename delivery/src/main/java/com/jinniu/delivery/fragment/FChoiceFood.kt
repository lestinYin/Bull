package com.jinniu.delivery.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jinniu.delivery.*
import com.jinniu.delivery.activity.AFoodDetail
import com.jinniu.delivery.activity.AXuanGuiGe
import com.lestin.yin.MyApplication
import com.lestin.yin.base.FBase
import com.lestin.yin.utils.image.ShowImage
import kotlinx.android.synthetic.main.merchant_page_food_layout.*
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import com.oushangfeng.pinnedsectionitemdecoration.utils.FullSpanUtil
import java.io.Serializable
import android.support.v7.widget.LinearLayoutManager
import com.lestin.yin.utils.FlyUtils


/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.delivery.fragment
 * @ClassName:      FChoiceFood
 * @Description:     选菜列表
 * @Author:         lestin.yin
 * @CreateDate:     2019-08-20 17:36
 * @Version:        1.0
 */
class FChoiceFood : FBase() {

    var topHeight: Int = 0
    var recommendHeight: Int = 0
    var mLeftShowList : MutableList<Int> = ArrayList()

    private val PARAM_STATUS = "status"

    companion object {
        private const val PARAM_STATUS = "status"

        fun newInstance(status: String): FChoiceFood {
            val fragment = FChoiceFood()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.arguments = args
            return fragment
        }
    }

    override fun layoutId(): Int = R.layout.merchant_page_food_layout

    override fun initData() {
        val foodDetails = Data.foodDetails()
        val foodAdapter = FoodAdapter(foodDetails)
        val sideAdapter = SideAdapter(Data.foodMenus())

        //保存右侧title位置
        for (i in foodDetails.indices ) {
            if (foodDetails[i] is FoodTitle) {
                mLeftShowList.add(i)
            }
        }


        //设置分界线吸顶
        vRecycler.addItemDecoration(PinnedHeaderItemDecoration.Builder(FoodAdapter.TYPE_TITLE)
                .setDividerId(R.drawable.transparent)
                .create())
        vRecycler.adapter = foodAdapter
        foodAdapter.openLoadAnimation()

        vRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var totalDy = 0

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

//                totalDy -= dy
//                val transY = if (totalDy > -(topHeight + recommendHeight)) totalDy else -(topHeight + recommendHeight)
//                vSide.translationY = transY.toFloat()

                val topPositions = (vRecycler.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                //滑动选中左边状态
                if (mLeftShowList.contains(topPositions)) {
                    sideAdapter.setSelectedPosition(mLeftShowList.indexOf(topPositions))
                }


            }
        })

        vSide.isNestedScrollingEnabled = false

        vSide.adapter = sideAdapter
        //点击item滚动到指定的位置
        sideAdapter.setOnItemClickListener { adapter, view, position ->
            sideAdapter.setSelectedPosition(position)
            (vRecycler.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(mLeftShowList[position],0)

        }
    }

    override fun initView() {
    }

    override fun start() {
    }

}



//class FoodTop : MultiItemEntity {
//    override fun getItemType(): Int = FoodAdapter.TYPE_TOP
//}
//
//class FoodRecommend(val data: List<FoodContent>) : MultiItemEntity {
//    override fun getItemType(): Int = FoodAdapter.TYPE_RECOMMEND
//}

class FoodCover(val url: Int) : MultiItemEntity {
    override fun getItemType(): Int = FoodAdapter.TYPE_CONTENT_IMAGE
}

class FoodTitle(val title: String, var isSelected : Boolean) : MultiItemEntity {
    override fun getItemType(): Int = FoodAdapter.TYPE_TITLE
}

class FoodContent(val name: String, val icon: Int, val desc: String, val price: Float,val specification : Boolean) : MultiItemEntity,Serializable {
    override fun getItemType(): Int = FoodAdapter.TYPE_CONTENT
}

class FoodAdapter(data: List<MultiItemEntity>) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) ,BaseQuickAdapter.OnItemClickListener{

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

        val food = data[position] as MultiItemEntity
        if (food.itemType == TYPE_CONTENT) {
            var intent = Intent(mContext, AFoodDetail::class.java)
            intent.putExtra("food", food as FoodContent)
            mContext.startActivity(intent)
            mContext.activity()?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }


    companion object {
        private var TYPE_PRODUCER = 1
//        val TYPE_TOP = TYPE_PRODUCER++
//        val TYPE_RECOMMEND = TYPE_PRODUCER++
        val TYPE_CONTENT_IMAGE = TYPE_PRODUCER++
        val TYPE_TITLE = TYPE_PRODUCER++
        val TYPE_CONTENT = TYPE_PRODUCER++
    }


    init {
//        addItemType(TYPE_TOP, R.layout.merchant_food_list_top)
//        addItemType(TYPE_RECOMMEND, R.layout.merchant_food_list_recommend)
        addItemType(TYPE_CONTENT_IMAGE, R.layout.merchant_food_list_content_image)
        addItemType(TYPE_TITLE, R.layout.merchant_food_list_sticky_title)
        addItemType(TYPE_CONTENT, R.layout.merchant_food_list_content)
        this.onItemClickListener = this
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, TYPE_TITLE)
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        FullSpanUtil.onViewAttachedToWindow(holder, this, TYPE_TITLE)
    }




    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when (helper.itemViewType) {
//            TYPE_TOP -> {
//                helper.itemView.postDelayed1(Runnable { postEvent(Bus.FOOD_TOP_HEIGHT, helper.itemView.height) })
//            }
//            TYPE_RECOMMEND -> {
//                helper.itemView.postDelayed1(Runnable { postEvent(Bus.FOOD_RECOMMEND_HEIGHT, helper.itemView.height) })
//                val view: RecyclerView = helper.getView(R.id.vRecommends)
//                view.adapter = FoodRecommendCellAdapter((item as FoodRecommend).data)
//            }
            TYPE_CONTENT_IMAGE -> {
                (item as FoodCover).apply { helper.setImageUrl(R.id.vImage, url) }
            }
            TYPE_TITLE -> {
                (item as FoodTitle).apply { helper.setText(R.id.vTitle, title) }

            }
            TYPE_CONTENT -> {
                (item as FoodContent).apply {

                    helper.setText(R.id.vPrice, "$ $price")
                            .setText(R.id.vName, name)
                            .setText(R.id.vDesc, desc)
                            .setImageUrl(R.id.vIcon, icon)

                    val showNumber = helper.getView<LinearLayout>(R.id.ll_show_number)
                    val showSpecification = helper.getView<RelativeLayout>(R.id.rl_show_specificaiton)
                    val vAdd = helper.getView<ImageView>(R.id.vAdd)
                    val vLess = helper.getView<ImageView>(R.id.vLess)
                    val choiceNumber = helper.getView<TextView>(R.id.tv_cart_number)
                    //判断
                    if (item.specification) {
                        showNumber.visibility = View.GONE
                        showSpecification.visibility = View.VISIBLE
                    } else {
                        showNumber.visibility = View.VISIBLE
                        showSpecification.visibility = View.GONE
                    }
                    //点击选规格
                    showSpecification.setOnClickListener {
                        mContext.startActivity(Intent(mContext,AXuanGuiGe::class.java))
                        mContext.activity()!!.overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
                    }
                    //数量更改
                    vAdd.setOnClickListener {
                        val number = Integer.valueOf(choiceNumber.text.toString())
                        vLess.visibility = View.VISIBLE
                        choiceNumber.visibility = View.VISIBLE
                        choiceNumber.text = (number + 1).toString()

                        //开启动画
                        var startLocation = IntArray(2)
                        vAdd.getLocationInWindow(startLocation)
                        var flyRes:ImageView = ImageView(mContext)
                        flyRes.setBackgroundResource(R.mipmap.add)

                        var finalView = mContext.activity()?.findViewById<RelativeLayout>(R.id.include_shop_car)?.findViewById<TextView>(R.id.tv_shopcart_number)

                        mContext.activity()?.let { it1 -> FlyUtils.setAnim(it1,flyRes,startLocation, finalView!!) }
                    }
                    //点击减
                    vLess.setOnClickListener {
                        val number = Integer.valueOf(choiceNumber.text.toString())
                        if (number==1) {
                            vLess.visibility = View.GONE
                            choiceNumber.visibility = View.GONE
                        }
                        choiceNumber.text = (number - 1).toString()

                    }


                }
            }
        }

    }
}




//左侧列表
class SideAdapter(data: List<FoodTitle>) : BaseQuickAdapter<FoodTitle, BaseViewHolder>(data) {


    //选中位置
    private var mSelectedPosition : Int = 0
    init {
        mLayoutResId = R.layout.merchant_food_list_side
    }

    @SuppressLint("ResourceAsColor")
    override fun convert(helper: BaseViewHolder, item: FoodTitle) {
        item.apply {
            helper.setText(R.id.vTitle, title)
            if (item.isSelected) {
                helper.setBackgroundRes(R.id.vTitle_root, R.color.white)
            } else {
                helper.setBackgroundRes(R.id.vTitle_root, R.color.color_F6FBFF)
            }


        }

    }

    // leftAdapter的setSelectedPosition方法
    // 刷新之前选中的和刚选中的这两项
    fun setSelectedPosition(position: Int) {

        data[mSelectedPosition].isSelected = false
        notifyItemChanged(mSelectedPosition)
        data[position].isSelected = true
        notifyItemChanged(position)
        mSelectedPosition = position
    }

}



//显示图片
fun BaseViewHolder.setImageUrl(viewId: Int, imageUrl: Int): BaseViewHolder {
    val view: ImageView = getView(viewId)
    ShowImage.showRes(MyApplication.context,imageUrl,view,5)
//    view.load(imageUrl)
    return this
}