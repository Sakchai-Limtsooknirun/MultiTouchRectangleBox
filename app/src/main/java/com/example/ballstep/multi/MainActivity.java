package com.example.ballstep.multi;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MyRect> list=new ArrayList<>();
    MyView myview;
    MyRect test=null;
    int x1;
    int y1;
    int x2;
    int y2;
    int maxindex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         myview=new MyView(this);
        setContentView(myview);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int index = MotionEventCompat.getActionIndex(event);
        if (maxindex<index){
            maxindex=index;
        }
        try {
            for (int i = 0; i <= maxindex; i++) {
                if (i==0){
                    x1=(int) MotionEventCompat.getX(event, i);
                    y1=(int) MotionEventCompat.getY(event, i);
                }
                if(i==1){
                    x2=(int) MotionEventCompat.getX(event, i);
                    y2=(int) MotionEventCompat.getY(event, i);
                }
            }
        } catch (Exception e) {

        }
        if (event.getPointerCount()==2){
            test=new MyRect(x1,y1,x2,y2);
            myview.invalidate();
        }
        if (event.getPointerCount()<2 && test!=null){
            list.add(test);
            test=null;
            myview.invalidate();
        }

        return super.onTouchEvent(event);
    }

    public class MyView extends View {
        public MyView(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {

            if(test!=null){
                Paint paint=new Paint();
                paint.setColor(Color.DKGRAY);
                canvas.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.max(x1,x2),Math.max(y1,y2),paint);
            }
            for (MyRect r:list ){
                Paint paint=new Paint();
                paint.setColor(Color.DKGRAY);
                canvas.drawRect(Math.min(r.x1,r.x2),Math.min(r.y1,r.y2),Math.max(r.x1,r.x2),Math.max(r.y1,r.y2),paint);
            }
        }
    }

    public class  MyRect{
        int x1;
        int y1;
        int x2;
        int y2;
        public  MyRect(int x1,int y1,int x2,int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }

    }
}
