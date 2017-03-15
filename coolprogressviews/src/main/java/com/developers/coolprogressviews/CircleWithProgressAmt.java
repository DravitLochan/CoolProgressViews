package com.developers.coolprogressviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DravitLochan on 15-03-2017.
 */

public class CircleWithProgressAmt extends View {

    Paint paint = new Paint();
    RectF oval = new RectF();
    int startAngle = 0;
    int sweepAngle = 0;
    int progress=3;

    private float radius2;
    private int circleColor2;
    private int arcColor2;

    public CircleWithProgressAmt(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleWithArcProgress, 0, 0);
        try {
            radius2 = array.getDimension(R.styleable.CircleWithProgressAmt_arcColor2, 50);
            circleColor2 = array.getColor(R.styleable.CircleWithProgressAmt_circleColor2, Color.parseColor("#b0dbdb"));
            arcColor2 = array.getColor(R.styleable.CircleWithProgressAmt_arcColor2, Color.parseColor("#097669"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            array.recycle();
        }
        post(animator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setColor(circleColor2);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius2, paint);

        paint.setColor(arcColor2);
        oval.set(getWidth() / 2 - radius2, getHeight() / 2 - radius2, getWidth() / 2 + radius2, getHeight() / 2 + radius2);
        canvas.drawArc(oval, startAngle, sweepAngle, false, paint);
    }

    Runnable animator = new Runnable() {
        @Override
        public void run() {
            sweepAngle+=getProgress();
            invalidate();
            postDelayed(this, 30);
        }
    };

    public void setProgress(int progress)
    {
        this.progress = progress;
    }

    public int getProgress()
    {
        return progress;
    }
}
