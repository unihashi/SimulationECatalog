package com.simulation.simulationecatalog.data.components;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;

import com.simulation.simulationecatalog.R;

public class CustomButton extends AppCompatButton {

    private int color;
    private int bordercolor;
    private GradientDrawable gradientDrawable;
    private ColorStateList colorStateList;
    private RippleDrawable rippleDrawable;
    private TypedArray a;
    private boolean showborder;
    private int bordersize;
    private int redius;
    private ShapeDrawable shapeDrawable;
    private int colorRipple;
    private LayerDrawable layer;

    public CustomButton(Context context) {
        super(context);
        init(null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attr) {
        setHeight(40);
        setGravity(Gravity.CENTER);
        setElevation(4);
        if (attr != null) {
            a = getContext().getTheme().obtainStyledAttributes(attr, R.styleable.CustomButton, 0, 0);
            shapeDrawable = new ShapeDrawable();
            shapeDrawable.setPadding(5, 7, 5, 10);
            shapeDrawable.setTint(getResources().getColor(R.color.default_transparent));

            showborder = a.getBoolean(R.styleable.CustomButton_bl_showborder, false);
            bordersize = a.getDimensionPixelOffset(R.styleable.CustomButton_bl_bordersize, 0);
            bordercolor = a.getColor(R.styleable.CustomButton_bl_bordercolor, Color.parseColor("#000000"));
            redius = a.getDimensionPixelOffset(R.styleable.CustomButton_bl_redius, 0);
            color = a.getColor(R.styleable.CustomButton_bl_bgcolor, getResources().getColor(R.color.default_transparent));
            colorRipple = a.getColor(R.styleable.CustomButton_bl_ripleColor, getResources().getColor(R.color.default_ripple));
            boolean enable = a.getBoolean(R.styleable.CustomButton_bl_enable, true);
            setButtonEnable(enable);

            try {
                if (showborder) {
                    gradientDrawable = new GradientDrawable();
                    gradientDrawable.setStroke(bordersize, bordercolor);
                    gradientDrawable.setColor(color);
                    gradientDrawable.setCornerRadius(redius);
                    GradientDrawable gradientDrawableMask = new GradientDrawable();
                    gradientDrawableMask.setColor(getResources().getColor(R.color.white));
                    gradientDrawableMask.setCornerRadius(redius);
                    colorStateList = ColorStateList.valueOf(colorRipple);
                    rippleDrawable = new RippleDrawable(colorStateList, gradientDrawable, gradientDrawableMask);

                    Drawable[] drawables = {shapeDrawable, rippleDrawable};
                    layer = new LayerDrawable(drawables);


                    setBackground(layer);
                }
            } finally {
                a.recycle();
            }
        }
    }

    public void setButtonEnable(boolean b) {
        if (b) {
            this.setEnabled(true);
            this.setAlpha(1);
        } else {
            this.setEnabled(false);
            this.setAlpha(0.6f);
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
        if (showborder) {
            gradientDrawable.setStroke(bordersize, bordercolor);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                rippleDrawable = new RippleDrawable(colorStateList, gradientDrawable, null);
                setBackground(rippleDrawable);
            } else {
                setBackground(gradientDrawable);
            }
        }
    }

    public int getBordersize() {
        return bordersize;
    }

    public void setBordercolor(int bordercolor){
        this.bordercolor = bordercolor;
    }

    public int getBordercolor() {
        return bordercolor;
    }
}