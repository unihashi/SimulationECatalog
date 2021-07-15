package com.simulation.simulationecatalog.data.components;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.simulation.simulationecatalog.R;

public class CustomTextView extends AppCompatTextView {

    private int color;
    private int bordercolor;
    private GradientDrawable gradientDrawable;
    private ColorStateList colorStateList;
    private RippleDrawable rippleDrawable;
    private TypedArray a;
    private boolean showborder;
    private int bordersize;
    private int redius;

    public CustomTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attr) {
        if (attr != null) {
            a = getContext().getTheme().obtainStyledAttributes(attr, R.styleable.CustomTextView, 0, 0);

            showborder = a.getBoolean(R.styleable.CustomTextView_ctv_showborder, false);
            bordersize = a.getDimensionPixelOffset(R.styleable.CustomTextView_ctv_bordersize, 0);
            bordercolor = a.getColor(R.styleable.CustomTextView_ctv_bordercolor, Color.parseColor("#000000"));
            redius = a.getDimensionPixelOffset(R.styleable.CustomTextView_ctv_redius, 0);
            color = a.getColor(R.styleable.CustomTextView_ctv_bgcolor, Color.parseColor("#FFFFFF"));
            try {
                if (showborder) {
                    gradientDrawable = new GradientDrawable();
                    gradientDrawable.setStroke(bordersize, bordercolor);
                    gradientDrawable.setCornerRadius(redius);
                    gradientDrawable.setColor(color);
                    setBackground(gradientDrawable);
                }
            } finally {
                a.recycle();
            }
        }
    }

    public void setColor(int color) {
        this.color = color;
        if (showborder) {
            gradientDrawable.setColor(color);
            gradientDrawable.setStroke(bordersize, bordercolor);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                rippleDrawable = new RippleDrawable(colorStateList, gradientDrawable, null);
                setBackground(rippleDrawable);
            } else {
                setBackground(gradientDrawable);
            }
        }
    }

    public void setBordersize(int bordersize) {
        this.bordersize = bordersize;
    }

    public int getBordersize() {
        return bordersize;
    }
}