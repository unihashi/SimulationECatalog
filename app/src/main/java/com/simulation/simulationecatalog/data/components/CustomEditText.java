package com.simulation.simulationecatalog.data.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;

import com.simulation.simulationecatalog.R;

public class CustomEditText extends AppCompatEditText {

    private Drawable dRight;
    private Rect rBounds;

    public CustomEditText(Context context) {
        super(context);
        init(null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attr) {
        if (attr != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attr, R.styleable.CustomEditText, 0, 0);
            boolean showBorder = a.getBoolean(R.styleable.CustomEditText_cedt_showborder, false);
            int borderSize = a.getDimensionPixelOffset(R.styleable.CustomEditText_cedt_bordersize, 0);
            int cornerRedius = a.getDimensionPixelOffset(R.styleable.CustomEditText_cedt_redius, 0);
            int bgcolor = a.getColor(R.styleable.CustomEditText_cedt_bgcolor, Color.GRAY);
            int borderColor = a.getColor(R.styleable.CustomEditText_cedt_bordercolor, Color.parseColor("#000000"));
            int[] colors = {bgcolor, bgcolor, bgcolor};
            try {
                if (showBorder) {
                    GradientDrawable gd = new GradientDrawable();
                    gd.setStroke(borderSize, borderColor);
                    gd.setCornerRadius(cornerRedius);
                    gd.setColor(bgcolor);
                    gd.setColors(colors);
                    setBackground(gd);
                }
            } finally {
                a.recycle();
            }
        }
    }
    @Override
    public void setCompoundDrawables(Drawable left, Drawable top,
                                     Drawable right, Drawable bottom)
    {
        if(right !=null)
        {
            dRight = right;
        }
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        if(event.getAction() == MotionEvent.ACTION_UP && dRight!=null)
        {
            rBounds = dRight.getBounds();
            final int x = (int)event.getX();
            final int y = (int)event.getY();
            //System.out.println("x:/y: "+x+"/"+y);
            //System.out.println("bounds: "+bounds.left+"/"+bounds.right+"/"+bounds.top+"/"+bounds.bottom);
            //check to make sure the touch event was within the bounds of the drawable
            if(x>=(this.getRight()-rBounds.width()) && x<=(this.getRight()-this.getPaddingRight())
                    && y>=this.getPaddingTop() && y<=(this.getHeight()-this.getPaddingBottom()))
            {
                //System.out.println("touch");
                this.setText("");
                event.setAction(MotionEvent.ACTION_CANCEL);//use this to prevent the keyboard from coming up
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable
    {
        dRight = null;
        rBounds = null;
        super.finalize();
    }
}