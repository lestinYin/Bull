package com.jinniu.my.adapter

import android.support.annotation.NonNull
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jinniu.my.R
import com.jinniu.my.activity.Leavel0
import com.jinniu.my.activity.Leavel1

/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.jinniu.my.adapter
 * @ClassName:      QuestionAdapter
 * @Description:     问题adapter
 * @Author:         lestin.yin
 * @CreateDate:     2019-09-12 16:26
 * @Version:        1.0
 */
class QuestionAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    val TYPE_LEVEL_0 = 0
    val TYPE_LEVEL_1 = 1


    constructor(data: List<MultiItemEntity>) : super(data) {
        addItemType(TYPE_LEVEL_0,R.layout.item_quetion)
        addItemType(TYPE_LEVEL_1,R.layout.item_question_sub)

    }


    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        when (helper!!.itemViewType) {

            TYPE_LEVEL_0 ->{
                var leavel0 = item as Leavel0

                helper.setText(R.id.tv_title,leavel0.title)

                helper.itemView.setOnClickListener {

                    var pos = helper.adapterPosition
                    if (leavel0.isExpanded) {
                        collapse(pos,false)
                    } else {
                        expand(pos,false)
                    }
                }

            }
            TYPE_LEVEL_1 ->{
                var leavel1 = item as Leavel1
                helper.setText(R.id.tv_content,leavel1.content)
            }
        }
    }
}