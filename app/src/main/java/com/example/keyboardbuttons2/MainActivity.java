package com.example.keyboardbuttons2;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

        if(aLayout != null) {
//            aLayout.setBackgroundColor(Color.CYAN);
        }

        View aView1 = new View(this);
        aView1.setId(1066);
        aView1.setLayoutParams(new ConstraintLayout.LayoutParams(round(aDisplayScale * 50),
                round(aDisplayScale * 200)));
        aView1.setBackgroundColor(Color.RED);
        aLayout.addView(aView1);

        ConstraintSet aConstraints = new ConstraintSet();
        aConstraints.clone(aLayout);
        aConstraints.connect(aView1.getId(),
                ConstraintSet.LEFT,
                aLayout.getId(),
                ConstraintSet.LEFT,
                round(aDisplayScale * 50));
        aConstraints.applyTo(aLayout);

    }
}
