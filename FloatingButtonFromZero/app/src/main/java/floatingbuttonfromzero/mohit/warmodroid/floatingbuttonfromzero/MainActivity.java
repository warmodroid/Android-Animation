package floatingbuttonfromzero.mohit.warmodroid.floatingbuttonfromzero;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    FloatingActionButton fabOne, fabTwo, fabThree;
    private Animation show_fab_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fabOne = (FloatingActionButton) findViewById(R.id.fab_1);


        //Animation
        show_fab_one = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_one_show);


        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fabOne.getLayoutParams();
        layoutParams.rightMargin += (int) (fabOne.getWidth()*1.7);
        layoutParams.bottomMargin += (int) (fabOne.getHeight()*0.25);
        fabOne.setLayoutParams(layoutParams);
        fabOne.setClickable(true);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabOne.startAnimation(show_fab_one);
            }
        });
    }
}
