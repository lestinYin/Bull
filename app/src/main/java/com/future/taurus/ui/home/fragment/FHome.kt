package com.future.taurus.ui.home.fragment

import android.os.Bundle
import com.future.taurus.R
import com.future.taurus.base.FBase

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
class FHome : FBase(){
    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun start() {
    }

    private val PARAM_STATUS = "status"

    companion object {
        private val PARAM_STATUS = "status"

        fun newInstance(status: String): FHome {
            val fragment = FHome()
            val args = Bundle()
            args.putString(PARAM_STATUS, status)
            fragment.setArguments(args)
            return fragment
        }
    }
}