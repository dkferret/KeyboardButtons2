package com.example.keyboardbuttons2;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int kNumKeys = 6; // 88 for full keyboard
    static final int kLocationOffset = 50;
    static final int kKeyId = 1000; // this may change
    static final int kMidiMin = 21; //A0 inclusive
    static final int kMidiMax = 108; //C8  inclusive
    static final int kMidiC4 = 60; // this is for testing

    int anInitialColor = Color.GREEN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView aHello = this.findViewById(R.id.helloTextView);
        ConstraintLayout aLayout = this.findViewById(R.id.outerConstraintLayout);

        DisplayMetrics aDisplayMetrics = getResources().getDisplayMetrics();
        float aDisplayScale = aDisplayMetrics.densityDpi / 160.0f;
        // magic# 160 is from ratio of first device

        if(aHello != null) {
            aHello.setText("goodbye world");
        }

        if(aLayout == null) {
            Log.d("log","error: null aLayout");
            return;
        }
        aLayout.setBackgroundColor(Color.CYAN);

        View aView1 = new View(this);
        aView1.setId(1066);
        aView1.setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 50),
                round(aDisplayScale * 200)));
        aView1.setBackgroundColor(Color.GREEN);
        aLayout.addView(aView1);

        // Fills screen with kNum rectangles
        View[] aKeyboardView = new View[kNumKeys];

        aKeyboardView[0] = new View(this);
        aKeyboardView[0].setId(kKeyId);
        aKeyboardView[0].setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 50),
                round(aDisplayScale * 200)));
        anInitialColor = Color.BLUE;
        aKeyboardView[0].setBackgroundColor(anInitialColor);
        aKeyboardView[0].setOnTouchListener(new KeyTouchListener(kMidiC4, anInitialColor));
        aLayout.addView(aKeyboardView[0]);

        for (int i = 1; i<kNumKeys; i++) {
            Log.d("log","loop i == " + i );
            aKeyboardView[i] = new View(this);
            aKeyboardView[i].setId(kKeyId + i);
            aKeyboardView[i].setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 50),
                    round(aDisplayScale * 200)));
            if ((i%2) == 1) {
                //aKeyboardView[i].setBackgroundColor(Color.RED);
                anInitialColor = Color.RED;
            }
            else {
                //aKeyboardView[i].setBackgroundColor(Color.BLUE);
                anInitialColor = Color.BLUE;
            }
            aKeyboardView[i].setBackgroundColor(anInitialColor);
            aKeyboardView[i].setOnTouchListener(new KeyTouchListener(kMidiC4+i,anInitialColor));

            aLayout.addView(aKeyboardView[i]);

        }


        ConstraintSet aConstraints = new ConstraintSet();
        aConstraints.clone(aLayout);

        aConstraints.connect(aView1.getId(),
                ConstraintSet.LEFT,
                aLayout.getId(),
                ConstraintSet.LEFT,
                round(aDisplayScale * 50));

//        aConstraints.applyTo(aLayout);

        for (int i = 0; i<kNumKeys; i++) {
            aConstraints.connect(aKeyboardView[i].getId(),
                    ConstraintSet.LEFT,
                    aLayout.getId(),
                    ConstraintSet.LEFT,
                    round(aDisplayScale * (kLocationOffset * i)));

        }
        aConstraints.applyTo(aLayout);
    }
}

