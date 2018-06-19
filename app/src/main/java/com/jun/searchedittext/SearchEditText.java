package com.jun.searchedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

public class SearchEditText extends EditText {
    private String hint;
    private float iconSpacing;
    private float searchIconDimen;
    private int textColorHint;
    private float textSizeHint;
    private Drawable drawable;
    private Paint paint;

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.searchEditText);
        float density = context.getResources().getDisplayMetrics().density;
        hint = ta.getString(R.styleable.searchEditText_hint);
        iconSpacing = ta.getDimension(R.styleable.searchEditText_iconSpacing, density * 5 + 0.5F);
        searchIconDimen = ta.getDimension(R.styleable.searchEditText_searchIconDimen, density * 15 + 0.5F);
        textColorHint = ta.getColor(R.styleable.searchEditText_textColorHint, 0xFF999999);
        textSizeHint = ta.getDimension(R.styleable.searchEditText_textSizeHint, density * 14 + 0.5F);
        drawable = getContext().getResources().getDrawable(ta.getResourceId(R.styleable.searchEditText_searchIcon, R.mipmap.ic_search));
        drawable.setBounds(0, 0, (int) searchIconDimen, (int) searchIconDimen);
        ta.recycle();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(textColorHint);
        paint.setTextSize(textSizeHint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(this.getText().toString())) {
            float textWidth = paint.measureText(hint);
            float textHeight = paint.getFontMetrics().bottom - paint.getFontMetrics().top;
            float x = (getWidth() - textWidth - searchIconDimen - iconSpacing) / 2;
            float y = (getHeight() - searchIconDimen) / 2;
            canvas.save();
            canvas.translate(x + getScrollX(), y + getScrollY());
            drawable.draw(canvas);
            canvas.drawText(hint, searchIconDimen + iconSpacing + getScrollX(), getHeight() - (getHeight() - textHeight) / 2 + getScrollY() - paint.getFontMetrics().bottom - y, paint);
            canvas.restore();
        }
    }
}