package com.future.taurus.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.future.taurus.R;



public class MineItemView extends RelativeLayout {
    private Context context;
    private String textName;
    private boolean showLine;

    private ImageView headImg;
    private TextView mName;
    private int img;
    private View line;


    public MineItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initLayout(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {

            TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MainItem, 0, 0);
            this.img = typeArray
                    .getResourceId(R.styleable.MainItem_img,
                            attrs.getAttributeResourceValue(null, "src", 0));
            textName = typeArray.getString(R.styleable.MainItem_name);
            showLine = typeArray.getBoolean(R.styleable.MainItem_line, true);
            this.context = context;
            typeArray.recycle();
        }
    }

    private void initLayout(Context context, AttributeSet attrs) {
        LayoutInflater.from(context)
                .inflate(R.layout.item_mine, this);
        this.headImg =  findViewById(R.id.iv_img);
        this.mName =  findViewById(R.id.tv_name);
        this.line =  findViewById(R.id.line);

        mName.setText(textName);
        if (!showLine) line.setVisibility(GONE);

        headImg.setBackgroundResource(img);

    }
}
