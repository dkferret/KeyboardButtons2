package com.example.keyboardbuttons2;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class KeyTouchListener implements View.OnTouchListener {
    int mMidiNote;
    int mBaseColor;
    KeyTouchListener(int inMidiNote, int inBaseColor) {
        super();
        mMidiNote = inMidiNote;
        mBaseColor = inBaseColor;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("log","action down, midiNote: " + mMidiNote);
            v.setBackgroundColor(v.getContext().getResources().getColor(R.color.yellow));
            return true; // this says Action_Down handled
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d("log","action up, midiNote: " + mMidiNote);
            v.performClick(); //only on release
            v.setBackgroundColor(mBaseColor);
            return true; // this says Action_Up handled
        } else {
            Log.d("log", "Unexpected action: " + event.getAction());
        }

        return false; // action was not handled
    }
}
