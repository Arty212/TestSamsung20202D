package com.example.testsamsung2020;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }


    public class MyView extends SurfaceView implements SurfaceHolder.Callback {

        private float x;
        private float y;

        public MyView(Context context) {
            super(context);
            getHolder().addCallback(this);
            this.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        x = event.getX();
                        y = event.getY();
                        invalidate();
                    }
                    return true;
                }
            });
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            MyThread thread = new MyThread(holder);
            thread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

        private class MyThread extends Thread {

            private SurfaceHolder holder;
            private final Paint paint = new Paint();

            public MyThread(SurfaceHolder holder) {
                this.holder = holder;
            }

            @Override
            public void run() {
                Canvas canvas = null;
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setColor(Color.RED);
                paint.setAntiAlias(true);
                while (true) {
                    canvas = holder.lockCanvas();
                    canvas.drawColor(Color.GREEN);
                    canvas.drawCircle(x, y, 100, paint);
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }



    /*public class MyView extends View {

        private float x;
        private float y;
        private final Paint paint = new Paint();


        public MyView(Context context) {
            super(context);

            this.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        x = event.getX();
                        y = event.getY();
                        invalidate();
                    }
                    return true;
                }
            });

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            canvas.drawCircle(x, y, 100, paint);
        }
    }*/

}