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

    static final int kHistoricMagic = 1066;
    static final int kNumKeys = 6; // 88 for full keyboard
    static final int kNumWKeys = 7;
    static final int kNumBKeys = 12;
    static final int kWLocationOffset = 50;
    static final int kBLocationOffset = 30;
    static final int kWhiteKeyId = 2000; // this may change
    static final int kBlackKeyId = 4000;
    static final int kMidiMin = 21; //A0 inclusive
    static final int kMidiMax = 108; //C8  inclusive
    static final int kMidiC4 = 60; // this is for testing

    int anInitialColor = Color.GREEN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView aHello = this.findViewById(R.id.helloTextView);
        ConstraintLayout alayout = this.findViewById(R.id.outerConstraintLayout);

        DisplayMetrics aDisplayMetrics = getResources().getDisplayMetrics();
        float aDisplayScale = aDisplayMetrics.densityDpi / 160.0f;
        // magic# 160 is from ratio of first device

        if (aHello != null) {
            aHello.setText("goodbye world");
        }

        if (alayout == null) {
            Log.d("log", "error: null aLayout");
            return;
        }
        alayout.setBackgroundColor(Color.LTGRAY);

        View aView1 = new View(this);
        aView1.setId(kHistoricMagic);
        aView1.setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 50),
                round(aDisplayScale * 200)));
        aView1.setBackgroundColor(Color.GREEN);
        alayout.addView(aView1);

        // Generate "white keys" then "black keys"
        View[] aWhiteKeyboardView = new View[kNumWKeys];
        View[] aBlackKeyboardView = new View[kNumBKeys];

        // This generates the white keys on a keyboard
        aWhiteKeyboardView[0] = new View(this);
        aWhiteKeyboardView[0].setId(kWhiteKeyId);
        aWhiteKeyboardView[0].setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 50),
                round(aDisplayScale * 200)));
        anInitialColor = Color.BLUE;
        aWhiteKeyboardView[0].setBackgroundColor(anInitialColor);
        aWhiteKeyboardView[0].setOnTouchListener(new KeyTouchListener(kMidiC4, anInitialColor));
        alayout.addView(aWhiteKeyboardView[0]);

        for (int i = 1; i < kNumWKeys; i++) {
            Log.d("log", "white loop i == " + i);
            aWhiteKeyboardView[i] = new View(this);
            aWhiteKeyboardView[i].setId(kWhiteKeyId + i);
            aWhiteKeyboardView[i].setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 50),
                    round(aDisplayScale * 200)));
            if ((i % 2) == 1) {
                //aWhiteKeyboardView[i].setBackgroundColor(Color.RED);
                anInitialColor = Color.RED;
            } else {
                //aWhiteKeyboardView[i].setBackgroundColor(Color.BLUE);
                anInitialColor = Color.BLUE;
            }
            aWhiteKeyboardView[i].setBackgroundColor(anInitialColor);
            aWhiteKeyboardView[i].setOnTouchListener(new KeyTouchListener(kMidiC4 + i, anInitialColor));

            alayout.addView(aWhiteKeyboardView[i]);

        }


        // constraints on white keys
        ConstraintSet aConstraints = new ConstraintSet();
        aConstraints.clone(alayout);

        aConstraints.connect(aView1.getId(),
                ConstraintSet.LEFT,
                alayout.getId(),
                ConstraintSet.LEFT,
                round(aDisplayScale * 50));

//        aConstraints.applyTo(aLayout);

        for (int i = 0; i < kNumWKeys; i++) {
            aConstraints.connect(aWhiteKeyboardView[i].getId(),
                    ConstraintSet.LEFT,
                    alayout.getId(),
                    ConstraintSet.LEFT,
                    round(aDisplayScale * (kWLocationOffset * i)));

        }
        aConstraints.applyTo(alayout);

        View aView2 = new View(this);
        aView2.setId(kHistoricMagic + 1);
        aView2.setLayoutParams(new ConstraintLayout.LayoutParams(

                round(aDisplayScale * 30),

                round(aDisplayScale * 120)));
        aView2.setBackgroundColor(Color.MAGENTA);
        alayout.addView(aView2);

        // This generates the black keys on a keyboard
        aBlackKeyboardView[0] = new View(this);
        aBlackKeyboardView[0].setId(kBlackKeyId);
        aBlackKeyboardView[0].setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 30),
                round(aDisplayScale * 120)));
        anInitialColor = Color.CYAN;
        aBlackKeyboardView[0].setBackgroundColor(anInitialColor);
        aBlackKeyboardView[0].setOnTouchListener(new KeyTouchListener(kMidiMin, anInitialColor));
        alayout.addView(aBlackKeyboardView[0]);

        for (int i = 1; i < kNumBKeys; i++) {
            Log.d("log", "black loop i == " + i);
            aBlackKeyboardView[i] = new View(this);
            aBlackKeyboardView[i].setId(kBlackKeyId + i);
            aBlackKeyboardView[i].setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 30),
                    round(aDisplayScale * 120)));
            if ((i % 2) == 1) {
                anInitialColor = Color.MAGENTA;
            } else {
                anInitialColor = Color.CYAN;
            }
            aBlackKeyboardView[i].setBackgroundColor(anInitialColor);
            aBlackKeyboardView[i].setOnTouchListener(new KeyTouchListener(kMidiMin + i, anInitialColor));

            alayout.addView(aBlackKeyboardView[i]);
        }
        // constraints on black keys
        ConstraintSet aBConstraints = new ConstraintSet();
        aBConstraints.clone(alayout);

        aBConstraints.connect(aView2.getId(),
                ConstraintSet.LEFT,
                alayout.getId(),
                ConstraintSet.LEFT,
                round(aDisplayScale * kBLocationOffset));

//        aBConstraints.applyTo(aLayout);

        for (int i = 0; i < kNumBKeys; i++) {
            aBConstraints.connect(aBlackKeyboardView[i].getId(),
                    ConstraintSet.LEFT,
                    alayout.getId(),
                    ConstraintSet.LEFT,
                    round(aDisplayScale * (kBLocationOffset * i)));

        }
        aBConstraints.applyTo(alayout);
    }
}

