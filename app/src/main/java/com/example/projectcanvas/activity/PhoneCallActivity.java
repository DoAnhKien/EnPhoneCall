package com.example.projectcanvas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projectcanvas.R;
import com.example.projectcanvas.views.ImageTouchSlider;

public class PhoneCallActivity extends AppCompatActivity {

    private ImageTouchSlider imageTouchSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);
        initViews();
    }

    private void initViews() {
        imageTouchSlider = findViewById(R.id.image_touch_slider);
        imageTouchSlider.setOnImageSliderChangedListener(new ImageTouchSlider.OnImageSliderChangedListener() {
            @Override
            public void onChanged() {

            }
        });
    }
}