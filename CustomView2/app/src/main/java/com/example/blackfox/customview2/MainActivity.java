package com.example.blackfox.customview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewGroup = (ViewGroup) findViewById(R.id.activity_main);


        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                moveButton();
                return true;
            }
        });

    }

    private void moveButton() {
        View btn = findViewById(R.id.button);
        TransitionManager.beginDelayedTransition(viewGroup);

        //To change the location of button
        RelativeLayout.LayoutParams newPositions = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        newPositions.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        newPositions.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);

        btn.setLayoutParams(newPositions);


        //Change the size of the button
        ViewGroup.LayoutParams sizeRules = btn.getLayoutParams();
        sizeRules.width = 450;
        sizeRules.height= 300;
        btn.setLayoutParams(sizeRules);

    }
}
