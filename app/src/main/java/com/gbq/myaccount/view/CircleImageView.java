package com.gbq.myaccount.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 圆环图片
 * Created by gbq on 2017-9-12.
 */

@SuppressLint("AppCompatCustomView")
public class CircleImageView extends ImageView {
    private final Resources mResources;
    private Paint mPaint;
    private Path mPath;

    public CircleImageView(Context context) {
        super(context);
        mResources = context.getResources();
        init();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mResources = context.getResources();
        init();
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        mResources = context.getResources();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 获取圆心的x坐标
        float centerX = getWidth() / 2f;
        // 获取圆心的y坐
        float centerY = getHeight() / 2f;
        float outerRadius = Math.max(centerX,centerY);
        mPaint.setAntiAlias(true);
        // 设置填充
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#FBA923"));
        mPath.addCircle(centerX, centerY, outerRadius, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);
        mPath.close();
        super.onDraw(canvas);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (null != drawable && drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                bitmap = getRoundBitmap(bitmap);
                drawable = new BitmapDrawable(mResources, bitmap);
            }
        }
        super.setImageDrawable(drawable);
    }

    private Bitmap getRoundBitmap(Bitmap bitmap) {
        int y = bitmap.getHeight() / 2;
        int x = bitmap.getWidth() / 2;
        int radius = Math.min(x, y) - 5;
        int bitmapWH = radius * 2;
        Bitmap outBitmap = Bitmap.createBitmap(bitmapWH, bitmapWH, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(outBitmap);
        final int color = 0xff424242;
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawCircle(radius, radius, radius - 15, paint);
        final Rect rect1 = new Rect(0, 0, bitmapWH, bitmapWH);
        final Rect rect2 = new Rect(x - radius, y - radius, x + radius, y + radius);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect2, rect1, paint);
        canvas.setBitmap(null);
        return outBitmap;
    }
}
