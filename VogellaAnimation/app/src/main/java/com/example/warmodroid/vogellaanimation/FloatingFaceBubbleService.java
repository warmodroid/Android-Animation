package com.example.warmodroid.vogellaanimation;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FloatingFaceBubbleService extends Service {
    private WindowManager windowManager;
    private LinearLayout ll;
    private Button stop;
    private WebView webView;
    private long touchStartTime;


    //private ImageView floatingFaceBubble;
    public void onCreate() {
        super.onCreate();
        //floatingFaceBubble = new ImageView(this);
        //a face floating bubble as imageView
        //floatingFaceBubble.setImageResource(R.mipmap.ic_launcher);
        windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        ll = new LinearLayout(this);
        stop = new Button(this);
        webView = new WebView(this);


        //params for webview
        ViewGroup.LayoutParams webLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(webLayoutParams);
        webView.loadUrl("http://www.google.com");




        //params for button;
        ViewGroup.LayoutParams btnParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        stop.setText("Close");
        stop.setLayoutParams(btnParams);

        //params for linear layout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        ll.setBackgroundColor(Color.argb(66,255,0,0));
        ll.setLayoutParams(layoutParams);



        //here is all the science of params
        final WindowManager.LayoutParams myParams = new WindowManager.LayoutParams(
                450,450,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        myParams.gravity = Gravity.TOP | Gravity.LEFT;
        myParams.x=0;
        myParams.y=0;


        //ll.addView(webView);
        //ll.addView(stop);
        
        
        
        // add a floatingfacebubble icon in window
        windowManager.addView(webView, myParams);
        try{
            //for moving the picture on touch and slide
            webView.setOnTouchListener(new View.OnTouchListener() {
                WindowManager.LayoutParams paramsT = myParams;
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //remove face bubble on long press
                    /*
                    if(System.currentTimeMillis()-touchStartTime> ViewConfiguration.getLongPressTimeout() && initialTouchX== event.getX()){
                        windowManager.removeView(floatingFaceBubble);

                        //Toast.makeText(v.getContext(),"clicked",Toast.LENGTH_LONG).show();
                        stopSelf();
                        return false;
                    }
                    */
                   // Toast.makeText(v.getContext(),"clicked",Toast.LENGTH_LONG).show();

                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            touchStartTime = System.currentTimeMillis();
                            initialX = myParams.x;
                            initialY = myParams.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            myParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                            myParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                            windowManager.updateViewLayout(v, myParams);

                            break;
                    }
                    return false;
                }
            });
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    windowManager.removeView(ll);
                    stopSelf();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
