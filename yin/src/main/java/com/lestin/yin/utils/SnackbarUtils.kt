package com.lestin.yin.utils

import android.annotation.TargetApi
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.lestin.yin.R
import java.lang.ref.WeakReference


/**
 * @name Pinche
 * @class name：com.xiaozhu.pinche.utils
 * @class describe
 * @author lestin.yin yinmaolin8@gmail.com
 * @time 2018/7/24 下午12:25
 * @change
 * @chang time
 * @class describe https://www.jianshu.com/p/f4ba05d7bbda
 */
object SnackbarUtils {
    //设置Snackbar背景颜色
//    private val color_info = -0xe47ec2
    private val color_info = -0xa7fdf
    private val color_confirm = -0xb34fb2
    private val color_warning = -0x13ffb
    private val color_danger = -0xbbcca
    //工具类当前持有的Snackbar实例
    private var snackbarWeakReference: WeakReference<Snackbar>? = null

    private fun SnackbarUtils() {
        throw RuntimeException("禁止无参创建实例")
    }

    private fun SnackbarUtils(snackbarWeakReference: WeakReference<Snackbar>?) {
        this.snackbarWeakReference = snackbarWeakReference
    }

    /**
     * 获取 mSnackbar
     * @return
     */
    fun getSnackbar(): Snackbar? {
        return if (this.snackbarWeakReference != null && this.snackbarWeakReference!!.get() != null) {
            this.snackbarWeakReference!!.get()
        } else {
            null
        }
    }

    /**
     * 初始化Snackbar实例
     * 展示时间:Snackbar.LENGTH_SHORT
     * @param view
     * @param message
     * @return
     */
    fun Short(view: View, message: String): SnackbarUtils {
        /*
        <view xmlns:android="http://schemas.android.com/apk/res/android"
          class="android.support.design.widget.Snackbar$SnackbarLayout"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:layout_gravity="bottom"
          android:theme="@style/ThemeOverlay.AppCompat.Dark"
          style="@style/Widget.Design.Snackbar" />
        <style name="Widget.Design.Snackbar" parent="android:Widget">
            <item name="android:minWidth">@dimen/design_snackbar_min_width</item>
            <item name="android:maxWidth">@dimen/design_snackbar_max_width</item>
            <item name="android:background">@drawable/design_snackbar_background</item>
            <item name="android:paddingLeft">@dimen/design_snackbar_padding_horizontal</item>
            <item name="android:paddingRight">@dimen/design_snackbar_padding_horizontal</item>
            <item name="elevation">@dimen/design_snackbar_elevation</item>
            <item name="maxActionInlineWidth">@dimen/design_snackbar_action_inline_max_width</item>
        </style>
        <shape xmlns:android="http://schemas.android.com/apk/res/android"
            android:shape="rectangle">
            <corners android:radius="@dimen/design_snackbar_background_corner_radius"/>
            <solid android:color="@color/design_snackbar_background_color"/>
        </shape>
        <color name="design_snackbar_background_color">#323232</color>
        */
        return SnackbarUtils(WeakReference(Snackbar.make(view, message, Snackbar.LENGTH_SHORT))).backColor(-0xcdcdce)
    }

    /**
     * 初始化Snackbar实例
     * 展示时间:Snackbar.LENGTH_LONG
     * @param view
     * @param message
     * @return
     */
    fun Long(view: View, message: String): SnackbarUtils {
        return SnackbarUtils(WeakReference(Snackbar.make(view, message, Snackbar.LENGTH_LONG))).backColor(-0xcdcdce)
    }

    /**
     * 初始化Snackbar实例
     * 展示时间:Snackbar.LENGTH_INDEFINITE
     * @param view
     * @param message
     * @return
     */
    fun Indefinite(view: View, message: String): SnackbarUtils {
        return SnackbarUtils(WeakReference(Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE))).backColor(-0xcdcdce)
    }

    /**
     * 初始化Snackbar实例
     * 展示时间:duration 毫秒
     * @param view
     * @param message
     * @param duration 展示时长(毫秒)
     * @return
     */
    fun Custom(view: View, message: String, duration: Int): SnackbarUtils {
        return SnackbarUtils(WeakReference(Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setDuration(duration))).backColor(-0xcdcdce)
    }

    /**
     * 设置mSnackbar背景色为  color_info
     */
    fun info(): SnackbarUtils {
        if (getSnackbar() != null) {
            getSnackbar()!!.view.setBackgroundColor(color_info)
        }
        return this
    }

    /**
     * 设置mSnackbar背景色为  color_confirm
     */
    fun confirm(): SnackbarUtils {
        if (getSnackbar() != null) {
            getSnackbar()!!.view.setBackgroundColor(color_confirm)
        }
        return this
    }

    /**
     * 设置Snackbar背景色为   color_warning
     */
    fun warning(): SnackbarUtils {
        if (getSnackbar() != null) {
            getSnackbar()!!.view.setBackgroundColor(color_warning)
        }
        return this
    }

    /**
     * 设置Snackbar背景色为   color_warning
     */
    fun danger(): SnackbarUtils {
        if (getSnackbar() != null) {
            getSnackbar()!!.view.setBackgroundColor(color_danger)
        }
        return this
    }

    /**
     * 设置Snackbar背景色
     * @param backgroundColor
     */
    fun Unit.backColor(@ColorInt backgroundColor: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            getSnackbar()!!.view.setBackgroundColor(backgroundColor)
        }
        return this@SnackbarUtils
    }

    /**
     * 设置TextView(@+id/snackbar_text)的文字颜色
     * @param messageColor
     */
    fun messageColor(@ColorInt messageColor: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            (getSnackbar()!!.view.findViewById<View>(R.id.snackbar_text) as TextView).setTextColor(messageColor)
        }
        return this
    }

    /**
     * 设置Button(@+id/snackbar_action)的文字颜色
     * @param actionTextColor
     */
    fun actionColor(@ColorInt actionTextColor: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            (getSnackbar()!!.view.findViewById<View>(R.id.snackbar_action) as Button).setTextColor(actionTextColor)
        }
        return this
    }

    /**
     * 设置   Snackbar背景色 + TextView(@+id/snackbar_text)的文字颜色 + Button(@+id/snackbar_action)的文字颜色
     * @param backgroundColor
     * @param messageColor
     * @param actionTextColor
     */
    fun colors(@ColorInt backgroundColor: Int, @ColorInt messageColor: Int, @ColorInt actionTextColor: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            getSnackbar()!!.view.setBackgroundColor(backgroundColor)
            (getSnackbar()!!.view.findViewById<View>(R.id.snackbar_text) as TextView).setTextColor(messageColor)
            (getSnackbar()!!.view.findViewById<View>(R.id.snackbar_action) as Button).setTextColor(actionTextColor)
        }
        return this
    }

    /**
     * 设置Snackbar 背景透明度
     * @param alpha
     * @return
     */
    fun alphas(alpha: Float): SnackbarUtils {
        var alpha = alpha
        if (getSnackbar() != null) {
            alpha = if (alpha >= 1.0f) 1.0f else if (alpha <= 0.0f) 0.0f else alpha
            getSnackbar()!!.view.alpha = alpha
        }
        return this
    }

    /**
     * 设置Snackbar显示的位置
     * @param gravity
     */
    fun gravityFrameLayout(gravity: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            val params = FrameLayout.LayoutParams(getSnackbar()!!.view.layoutParams.width, getSnackbar()!!.view.layoutParams.height)
            params.gravity = gravity
            getSnackbar()!!.view.layoutParams = params
        }
        return this
    }

    /**
     * 设置Snackbar显示的位置,当Snackbar和CoordinatorLayout组合使用的时候
     * @param gravity
     */
    fun gravityCoordinatorLayout(gravity: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            val params = CoordinatorLayout.LayoutParams(getSnackbar()!!.view.layoutParams.width, getSnackbar()!!.view.layoutParams.height)
            params.gravity = gravity
            getSnackbar()!!.view.layoutParams = params
        }
        return this
    }

    /**
     * 设置按钮文字内容 及 点击监听
     * [Snackbar.setAction]
     * @param resId
     * @param listener
     * @return
     */
    fun setAction(@StringRes resId: Int, listener: View.OnClickListener): SnackbarUtils {
        return if (getSnackbar() != null) {
            setAction(getSnackbar()!!.view.resources.getText(resId), listener)
        } else {
            this
        }
    }

    /**
     * 设置按钮文字内容 及 点击监听
     * [Snackbar.setAction]
     * @param text
     * @param listener
     * @return
     */
    fun setAction(text: CharSequence, listener: View.OnClickListener): SnackbarUtils {
        if (getSnackbar() != null) {
            getSnackbar()!!.setAction(text, listener)
        }
        return this
    }

    /**
     * 设置 mSnackbar 展示完成 及 隐藏完成 的监听
     * @param setCallback
     * @return
     */
    fun setCallback(setCallback: Snackbar.Callback): SnackbarUtils {
        if (getSnackbar() != null) {
            getSnackbar()!!.setCallback(setCallback)
        }
        return this
    }

    /**
     * 设置TextView(@+id/snackbar_text)左右两侧的图片
     * @param leftDrawable
     * @param rightDrawable
     * @return
     */
    fun leftAndRightDrawable(@DrawableRes leftDrawable: Int?, @DrawableRes rightDrawable: Int?): SnackbarUtils {
        if (getSnackbar() != null) {
            var drawableLeft: Drawable? = null
            var drawableRight: Drawable? = null
            if (leftDrawable != null) {
                try {
                    drawableLeft = getSnackbar()!!.view.resources.getDrawable(leftDrawable.toInt(),null)
                } catch (e: Exception) {
                }

            }
            if (rightDrawable != null) {
                try {
                    drawableRight = getSnackbar()!!.view.resources.getDrawable(rightDrawable.toInt(),null)
                } catch (e: Exception) {
                }

            }
            return leftAndRightDrawable(drawableLeft, drawableRight)
        } else {
            return this
        }
    }

    /**
     * 设置TextView(@+id/snackbar_text)左右两侧的图片
     * @param leftDrawable
     * @param rightDrawable
     * @return
     */
    fun leftAndRightDrawable(leftDrawable: Drawable?, rightDrawable: Drawable?): SnackbarUtils {
        if (getSnackbar() != null) {
            val message = getSnackbar()!!.view.findViewById<View>(R.id.snackbar_text) as TextView
            var paramsMessage = message.layoutParams as LinearLayout.LayoutParams
            paramsMessage = LinearLayout.LayoutParams(paramsMessage.width, paramsMessage.height, 0.0f)
            message.layoutParams = paramsMessage
            message.compoundDrawablePadding = message.paddingLeft
            val textSize = message.textSize.toInt()
            Log.e("Jet", "textSize:" + textSize)
            leftDrawable?.setBounds(0, 0, textSize, textSize)
            rightDrawable?.setBounds(0, 0, textSize, textSize)
            message.setCompoundDrawables(leftDrawable, null, rightDrawable, null)
            val paramsSpace = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
            (getSnackbar()!!.view as Snackbar.SnackbarLayout).addView(Space(getSnackbar()!!.view.context), 1, paramsSpace)
        }
        return this
    }

    /**
     * 设置TextView(@+id/snackbar_text)中文字的对齐方式 居中
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun messageCenter(): SnackbarUtils {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (getSnackbar() != null) {
                val message = getSnackbar()!!.view.findViewById<View>(R.id.snackbar_text) as TextView
                //View.setTextAlignment需要SDK>=17
                message.textAlignment = View.TEXT_ALIGNMENT_GRAVITY
                message.gravity = Gravity.CENTER
            }
        }
        return this
    }

    /**
     * 设置TextView(@+id/snackbar_text)中文字的对齐方式 居右
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun messageRight(): SnackbarUtils {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (getSnackbar() != null) {
                val message = getSnackbar()!!.view.findViewById<View>(R.id.snackbar_text) as TextView
                //View.setTextAlignment需要SDK>=17
                message.textAlignment = View.TEXT_ALIGNMENT_GRAVITY
                message.gravity = Gravity.CENTER_VERTICAL or Gravity.RIGHT
            }
        }
        return this
    }

    /**
     * 向Snackbar布局中添加View(Google不建议,复杂的布局应该使用DialogFragment进行展示)
     * @param layoutId  要添加的View的布局文件ID
     * @param index
     * @return
     */
    fun addView(layoutId: Int, index: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            //加载布局文件新建View
            val addView = LayoutInflater.from(getSnackbar()!!.view.context).inflate(layoutId, null)
            return addView(addView, index)
        } else {
            return this
        }
    }

    /**
     * 向Snackbar布局中添加View(Google不建议,复杂的布局应该使用DialogFragment进行展示)
     * @param addView
     * @param index
     * @return
     */
    fun addView(addView: View, index: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)//设置新建布局参数
            //设置新建View在Snackbar内垂直居中显示
            params.gravity = Gravity.CENTER_VERTICAL
            addView.layoutParams = params
            (getSnackbar()!!.view as Snackbar.SnackbarLayout).addView(addView, index)
        }
        return this
    }

    /**
     * 设置Snackbar布局的外边距
     * 注:经试验发现,调用margins后再调用 gravityFrameLayout,则margins无效.
     * 为保证margins有效,应该先调用 gravityFrameLayout,在 show() 之前调用 margins
     * @param margin
     * @return
     */
    fun margins(margin: Int): SnackbarUtils {
        return if (getSnackbar() != null) {
            margins(margin, margin, margin, margin)
        } else {
            this
        }
    }

    /**
     * 设置Snackbar布局的外边距
     * 注:经试验发现,调用margins后再调用 gravityFrameLayout,则margins无效.
     * 为保证margins有效,应该先调用 gravityFrameLayout,在 show() 之前调用 margins
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    fun margins(left: Int, top: Int, right: Int, bottom: Int): SnackbarUtils {
        if (getSnackbar() != null) {
            val params = getSnackbar()!!.view.layoutParams
            (params as ViewGroup.MarginLayoutParams).setMargins(left, top, right, bottom)
            getSnackbar()!!.view.layoutParams = params
        }
        return this
    }

    /**
     * 经试验发现:
     *      执行过{@link SnackbarUtils#backColor(int)}后:background instanceof ColorDrawable
     *      未执行过{@link SnackbarUtils#backColor(int)}:background instanceof GradientDrawable
     * @return
     */
    /*
    public SnackbarUtils radius(){
        Drawable background = snackbarWeakReference.get().getView().getBackground();
        if(background instanceof GradientDrawable){
            Log.e("Jet","radius():GradientDrawable");
        }
        if(background instanceof ColorDrawable){
            Log.e("Jet","radius():ColorDrawable");
        }
        if(background instanceof StateListDrawable){
            Log.e("Jet","radius():StateListDrawable");
        }
        Log.e("Jet","radius()background:"+background.getClass().getSimpleName());
        return new SnackbarUtils(mSnackbar);
    }
    */

    /**
     * 通过SnackBar现在的背景,获取其设置圆角值时候所需的GradientDrawable实例
     * @param backgroundOri
     * @return
     */
    private fun getRadiusDrawable(backgroundOri: Drawable): GradientDrawable? {
        var background: GradientDrawable? = null
        if (backgroundOri is GradientDrawable) {
            background = backgroundOri
        } else if (backgroundOri is ColorDrawable) {
            val backgroundColor = backgroundOri.color
            background = GradientDrawable()
            background.setColor(backgroundColor)
        } else {
        }
        return background
    }

    /**
     * 设置Snackbar布局的圆角半径值
     * @param radius    圆角半径
     * @return
     */
    fun radius(radius: Float): SnackbarUtils {
        var radius = radius
        if (getSnackbar() != null) {
            //将要设置给mSnackbar的背景
            val background = getRadiusDrawable(getSnackbar()!!.view.background)
            if (background != null) {
                radius = if (radius <= 0) 12F else radius
                background.cornerRadius = radius
                getSnackbar()!!.view.setBackgroundDrawable(background)
            }
        }
        return this
    }

    /**
     * 设置Snackbar布局的圆角半径值及边框颜色及边框宽度
     * @param radius
     * @param strokeWidth
     * @param strokeColor
     * @return
     */
    fun radius(radius: Int, strokeWidth: Int, @ColorInt strokeColor: Int): SnackbarUtils {
        var radius = radius
        var strokeWidth = strokeWidth
        if (getSnackbar() != null) {
            //将要设置给mSnackbar的背景
            val background = getRadiusDrawable(getSnackbar()!!.view.background)
            if (background != null) {
                radius = if (radius <= 0) 12 else radius
                strokeWidth = if (strokeWidth <= 0) 1 else if (strokeWidth >= getSnackbar()!!.view.findViewById<View>(R.id.snackbar_text).paddingTop) 2 else strokeWidth
                background.cornerRadius = radius.toFloat()
                background.setStroke(strokeWidth, strokeColor)
                getSnackbar()!!.view.setBackgroundDrawable(background)
            }
        }
        return this
    }

    /**
     * 设置Snackbar显示在指定View的下方
     * 注:暂时仅支持单行的Snackbar,因为[SnackbarUtils.calculateSnackBarHeight]暂时仅支持单行Snackbar的高度计算
     * @param targetView        指定View
     * @param contentViewTop    Activity中的View布局区域 距离屏幕顶端的距离
     * @param marginLeft        左边距
     * @param marginRight       右边距
     * @return
     */
    fun bellow(targetView: View, contentViewTop: Int, marginLeft: Int, marginRight: Int): SnackbarUtils {
        var marginLeft = marginLeft
        var marginRight = marginRight
        if (getSnackbar() != null) {
            marginLeft = if (marginLeft <= 0) 0 else marginLeft
            marginRight = if (marginRight <= 0) 0 else marginRight
            val locations = IntArray(2)
            targetView.getLocationOnScreen(locations)
            val snackbarHeight = calculateSnackBarHeight()
            val screenHeight = ViewUtil.getScreenHeight(getSnackbar()!!.view.context)
            //必须保证指定View的底部可见 且 单行Snackbar可以完整的展示
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //为什么要'+2'? 因为在Android L(Build.VERSION_CODES.LOLLIPOP)以上,例如Button会有一定的'阴影(shadow)',阴影的大小由'高度(elevation)'决定.
                //为了在Android L以上的系统中展示的Snackbar不要覆盖targetView的阴影部分太大比例,所以人为减小2px的layout_marginBottom属性.
                if (locations[1] + targetView.height >= contentViewTop && locations[1] + targetView.height + snackbarHeight + 2 <= screenHeight) {
                    gravityFrameLayout(Gravity.BOTTOM)
                    val params = getSnackbar()!!.view.layoutParams
                    (params as ViewGroup.MarginLayoutParams).setMargins(marginLeft, 0, marginRight, screenHeight - (locations[1] + targetView.height + snackbarHeight + 2))
                    getSnackbar()!!.view.layoutParams = params
                }
            } else {
                if (locations[1] + targetView.height >= contentViewTop && locations[1] + targetView.height + snackbarHeight <= screenHeight) {
                    gravityFrameLayout(Gravity.BOTTOM)
                    val params = getSnackbar()!!.view.layoutParams
                    (params as ViewGroup.MarginLayoutParams).setMargins(marginLeft, 0, marginRight, screenHeight - (locations[1] + targetView.height + snackbarHeight))
                    getSnackbar()!!.view.layoutParams = params
                }
            }
        }
        return this
    }

    /**
     * 计算单行的Snackbar的高度值(单位 pix)
     * @return
     */
    private fun calculateSnackBarHeight(): Int {
        /*
        <TextView
                android:id="@+id/snackbar_text"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:paddingTop="@dimen/design_snackbar_padding_vertical"
                android:paddingBottom="@dimen/design_snackbar_padding_vertical"
                android:paddingLeft="@dimen/design_snackbar_padding_horizontal"
                android:paddingRight="@dimen/design_snackbar_padding_horizontal"
                android:textAppearance="@style/TextAppearance.Design.Snackbar.Message"
                android:maxLines="@integer/design_snackbar_text_max_lines"
                android:layout_gravity="center_vertical|left|start"
                android:ellipsize="end"
                android:textAlignment="viewStart"/>
        */
        //文字高度+paddingTop+paddingBottom : 14sp + 14dp*2
        val SnackbarHeight = ViewUtil.dp2px(28) + ViewUtil.sp2px( 14F)
        Log.e("Jet", "直接获取MessageView高度:" + getSnackbar()!!.view.findViewById<View>(R.id.snackbar_text).height)
        return SnackbarHeight
    }


    /**
     * 显示 mSnackbar
     */
    fun show() {
        Log.e("Jet", "show()")
        if (getSnackbar() != null) {
            Log.e("Jet", "show")
            getSnackbar()!!.show()
        } else {
            Log.e("Jet", "已经被回收")
        }
    }
}

