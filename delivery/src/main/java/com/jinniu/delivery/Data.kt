package com.jinniu.delivery

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jinniu.delivery.fragment.*

object Data {
    fun foodDetails(): List<MultiItemEntity> {
        return listOf(
//                FoodTop(),
//                FoodRecommend(foodRecommends()),
                FoodTitle("进店须知",false),
                FoodCover(R.mipmap.pic1),
                FoodCover(R.mipmap.pic2),
                FoodCover(R.mipmap.pic3),
                FoodTitle("热销",false),
                FoodContent("皮蛋瘦肉粥", R.mipmap.pic4, "皮蛋瘦肉粥是非常受欢迎的粥，其中骚处自行体会..", 16F,false),
                FoodContent("香菇瘦肉粥", R.mipmap.pic5, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 16F,false),
                FoodContent("红枣桂圆粥", R.mipmap.pic6, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 16F,true),
                FoodTitle("养颜甜粥",false),

                FoodContent("小米南瓜粥", R.mipmap.pic7, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 14F,true),
                FoodContent("清火绿豆粥", R.mipmap.pic8, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 14F,false),
                FoodContent("红心地瓜粥", R.mipmap.pic9, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 14F,true),
                FoodContent("紫薯黑米粥", R.mipmap.pic10, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 16F,true),
                FoodContent("红枣桂圆粥", R.mipmap.pic6, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 16F,false),
                FoodContent("柠檬八宝粥", R.mipmap.pic11, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 17F,true),
                FoodTitle("营养水果粥",false),
                FoodContent("苹果雪梨粥", R.mipmap.pic12, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 16F,false),
                FoodContent("香蕉苹果粥", R.mipmap.pic13, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 16F,true),
                FoodContent("香蕉雪梨粥", R.mipmap.pic14, "对于吃货来说：治愈系没事，不过一碗热腾腾的粥", 16F,true),
                FoodTitle("",false),
                FoodTitle("",false),
                FoodTitle("",false),
                FoodTitle("",false)
        )
    }

    fun foodMenus(): List<FoodTitle> {
        return listOf(
                FoodTitle("进店须知",false),
                FoodTitle("热销",false),
                FoodTitle("养颜甜粥",false),
                FoodTitle("营养水果粥",false)
        )
    }

    private fun foodRecommends(): List<FoodContent> {
        return listOf(
                FoodContent("烧肉拼叉烧饭+肉包+可乐+虎邦辣椒酱", R.mipmap.pic15, "", 50.6F,true),
                FoodContent("绿豆汤+小肉+卤蛋+脆笋片", R.mipmap.pic16, "", 50.6F,true),
                FoodContent("冰镇绿豆汤", R.mipmap.pic15, "", 15F,true),
                FoodContent("台式炒饭+可乐+葱油饼套餐", R.mipmap.pic16, "", 33F,true)
        )
    }
}