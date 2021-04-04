package com.example.projectcanvas.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.projectcanvas.R;
import com.example.projectcanvas.callback.MyCallBack;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class CustomView extends View {

    private Paint mLeftPainCircle, mRightPainCircle;
    private float mLeftCircleX, mLeftCircleY, mRightCircleX, mRightCircleY;
    private float mCircleRadius = 100f;

    private boolean isEnd = false;
    private MyCallBack myCallBack;

    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    private Bitmap mPhoneCallBitmap, mEndPhoneCallBitmap, mIconNextBitmap, mIconBackBitmap;
    private Paint alphaPhoneCall, alphaEndPhoneCall, alphaIconBack, alphaIconNext;
    private float mPhoneCallX, mPhoneCallY, mEndPhoneCallX, mEndPhoneCallY;
    private float mIconNextX, mIconNextY, mIconBackX, mIconBackY;
    private float index;

    private Handler handler;
    private Runnable runnable;
    private ValueAnimator valueAnimator;


    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mLeftPainCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLeftPainCircle.setColor(Color.RED);
        mRightPainCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRightPainCircle.setColor(Color.GREEN);

        alphaPhoneCall = new Paint(ANTI_ALIAS_FLAG);
        alphaEndPhoneCall = new Paint(ANTI_ALIAS_FLAG);
        alphaIconBack = new Paint(ANTI_ALIAS_FLAG);
        alphaIconNext = new Paint(ANTI_ALIAS_FLAG);
        alphaEndPhoneCall.setAlpha(100);
        alphaPhoneCall.setAlpha(100);
        alphaIconNext.setAlpha(100);
        alphaIconBack.setAlpha(100);

        mPhoneCallBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_phone_call_a);
        mEndPhoneCallBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_end_phone_call_a);
        mIconNextBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_next);
        mIconNextBitmap = getResizedBitmap(mIconNextBitmap, 50, 50);
        mIconBackBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_back);
        mIconBackBitmap = getResizedBitmap(mIconBackBitmap, 50, 50);
        valueAnimator = new ValueAnimator();
        index = 150;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLeftCircleX == 0f || mLeftCircleY == 0f) {
            mLeftCircleX = 2 * (getWidth() / 10);
            mLeftCircleY = getWidth() / 2;
        }

        if (mRightCircleX == 0f || mRightCircleY == 0f) {
            mRightCircleX = 8 * (getWidth() / 10);
            mRightCircleY = getWidth() / 2;
        }

        if (mEndPhoneCallX == 0f || mEndPhoneCallY == 0f) {
            mEndPhoneCallX = 2 * (getWidth() / 10) - getWidth() / 25;
            mEndPhoneCallY = 4 * getWidth() / 10 + getWidth() / 20;
        }
        if (mPhoneCallX == 0f || mPhoneCallY == 0f) {
            mPhoneCallX = 8 * (getWidth() / 10) - getWidth() / 30;
            mPhoneCallY = 4 * getWidth() / 10 + getWidth() / 15;
        }
        if (mIconNextX == 0f || mIconNextY == 0f) {
            mIconNextX = 3 * (getWidth() / 10);
            mIconNextY = 5 * getWidth() / 10 - getWidth() / 25;
        }

        if (mIconBackX == 0f || mIconBackY == 0f) {
            mIconBackX = 7 * (getWidth() / 10) - getWidth() / 20;
            mIconBackY = 5 * getWidth() / 10 - getWidth() / 25;
        }
        canvas.drawCircle(mLeftCircleX, mLeftCircleY, mCircleRadius, mLeftPainCircle);
        canvas.drawCircle(mRightCircleX, mRightCircleY, mCircleRadius, mRightPainCircle);
        canvas.drawBitmap(mEndPhoneCallBitmap, mEndPhoneCallX, mEndPhoneCallY, alphaEndPhoneCall);
        canvas.drawBitmap(mPhoneCallBitmap, mPhoneCallX, mPhoneCallY, alphaPhoneCall);
        canvas.drawBitmap(mIconBackBitmap, mIconBackX, mIconBackY, alphaIconBack);
        canvas.drawBitmap(mIconNextBitmap, mIconNextX, mIconNextY, alphaIconNext);
        updateTheUi();
    }

    public void updateTheUi() {
        mIconNextX += index;
        mIconBackX -= index;
        Log.d("kienda mIcon x:", mIconNextX + "");
        handler = new Handler();
        runnable = () -> {
            postInvalidate();
            if (mIconNextX >= getWidth() / 2) {
                mIconNextX = 3 * (getWidth() / 10);
            }
            if (mIconBackX <= getWidth() / 2) {
                mIconBackX = 7 * (getWidth() / 10) - getWidth() / 20;
            }
        };
        handler.postDelayed(runnable, 500);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLeftCircleX = getWidth() / 2;
                mEndPhoneCallX = getWidth() / 2 - 45;
                alphaPhoneCall.setAlpha(0);
                mLeftPainCircle.setColor(Color.RED);
                mRightPainCircle.setColor(Color.WHITE);
                handler.removeCallbacks(runnable);
                handler.removeCallbacks(null);
                alphaIconBack.setAlpha(0);
                alphaIconNext.setAlpha(0);
                postInvalidate();
                Log.d("kienda", "ACTION_DOWN");
            case MotionEvent.ACTION_UP:
                mLeftCircleX = getWidth() / 2;
                mEndPhoneCallX = getWidth() / 2 - 45;
                alphaPhoneCall.setAlpha(0);
                mLeftPainCircle.setColor(Color.RED);
                mRightPainCircle.setColor(Color.WHITE);
                handler.removeCallbacks(runnable);
                handler.removeCallbacks(null);
                alphaIconBack.setAlpha(0);
                alphaIconNext.setAlpha(0);
                postInvalidate();
                Log.d("kienda", "ACTION_UP");
            case MotionEvent.ACTION_MOVE: {
                float x = event.getX();
                float y = event.getY();
                double dx = Math.pow(x - mLeftCircleX, 2);
                double dy = Math.pow(y - mLeftCircleY, 2);
                double dx1 = Math.pow(x - mRightCircleX, 2);
                double dy1 = Math.pow(y - mRightCircleY, 2);
                if (dx + dy <= Math.pow(mCircleRadius, 2)) {
                    if (x >= 2 * (getWidth() / 10) && x <= ((getWidth() / 2))) {
                        mLeftCircleX = x;
                        mEndPhoneCallX = x - 45;
                        mRightPainCircle.setColor(Color.GREEN);
                        alphaPhoneCall.setAlpha(100);
                        handler.removeCallbacks(runnable);
                        postInvalidate();
                        if (x >= 3 * (getWidth() / 10) && x < 4 * (getWidth() / 10)) {
                            mRightPainCircle.setColor(getResources().getColor(R.color.green_50));
                            mLeftCircleX = x;
                            mEndPhoneCallX = x - 45;
                            alphaPhoneCall.setAlpha(50);
                            handler.removeCallbacks(runnable);
                            postInvalidate();
                        } else if (x >= 4 * (getWidth() / 10) && x < ((getWidth() / 2) - 30)) {
                            mRightPainCircle.setColor(getResources().getColor(R.color.green_25));
                            mLeftCircleX = x;
                            mEndPhoneCallX = x - 30;
                            alphaPhoneCall.setAlpha(25);
                            postInvalidate();
                        } else if (x >= ((getWidth() / 2) - 30)) {
                            mRightPainCircle.setColor(Color.WHITE);
                            mLeftCircleX = x;
                            mEndPhoneCallX = x - 45;
                            alphaPhoneCall.setAlpha(0);
                            handler.removeCallbacks(runnable);
                            postInvalidate();
                        }
                    }
                } else if (dx1 + dy1 <= Math.pow(mCircleRadius, 2)) {
                    if (x >= (getWidth() / 2) && x <= (8 * (getWidth() / 10))) {
                        mLeftPainCircle.setColor(Color.RED);
                        mRightCircleX = x;
                        mPhoneCallX = x;
                        alphaEndPhoneCall.setAlpha(100);
                        handler.removeCallbacks(runnable);
                        postInvalidate();
                        if (x >= (getWidth() / 2) && x < 6 * (getWidth() / 10)) {
                            mLeftPainCircle.setColor(Color.WHITE);
                            mRightCircleX = x;
                            mPhoneCallX = x;
                            alphaEndPhoneCall.setAlpha(0);
                            handler.removeCallbacks(runnable);
                            postInvalidate();
                        }
                        if (x >= 6 * (getWidth() / 2) && x < 7 * (getWidth() / 10)) {
                            mLeftPainCircle.setColor(getResources().getColor(R.color.red_25));
                            mRightCircleX = x;
                            mPhoneCallX = x;
                            alphaEndPhoneCall.setAlpha(25);
                            handler.removeCallbacks(runnable);
                            postInvalidate();
                        }
                        if (x >= 7 * (getWidth() / 2) && x < 8 * (getWidth() / 10)) {
                            mLeftPainCircle.setColor(getResources().getColor(R.color.red_50));
                            mRightCircleX = x;
                            mPhoneCallX = x;
                            alphaEndPhoneCall.setAlpha(50);
                            handler.removeCallbacks(runnable);
                            postInvalidate();
                        } else if (x == 8 * (getWidth() / 10)) {
                            mLeftPainCircle.setColor(Color.RED);
                            mRightCircleX = x;
                            mPhoneCallX = x;
                            alphaEndPhoneCall.setAlpha(100);
                            handler.removeCallbacks(runnable);
                            postInvalidate();
                        }
                    }
                }
                return true;
            }

        }

        return value;
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int redWidth, int reqHeight) {
        Matrix matrix = new Matrix();
        RectF src = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(0, 0, redWidth, reqHeight);
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
