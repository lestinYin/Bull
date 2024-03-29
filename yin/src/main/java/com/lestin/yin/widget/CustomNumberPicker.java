package com.lestin.yin.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.lestin.yin.R;


/**
 * @author lestin.yin yinmaolin8@gmail.com
 * @name PreDiagnosis
 * @class name：com.doctorai.prediagnosis.ui.diagnosis.widget
 * @class describe
 * @time 2018/1/17 上午11:52
 * @change
 * @chang time
 * @class describe
 */


public class CustomNumberPicker extends NumberPicker {

    public CustomNumberPicker(Context context) {
        super(context);
    }

    public CustomNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    public void updateView(View view) {
        if (view instanceof EditText) {
            // 文字颜色、大小
            ((EditText) view).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            ((EditText) view).setTextSize(16f);
        }
    }

}
