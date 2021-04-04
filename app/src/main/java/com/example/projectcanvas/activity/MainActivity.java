package com.example.projectcanvas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcanvas.R;
import com.example.projectcanvas.callback.MyCallBack;
import com.example.projectcanvas.views.CustomView;

public class MainActivity extends AppCompatActivity {

    private CustomView customView;
    private Button btnSwapColor;
    private TextView tvTest;
    private boolean isEnd = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        listenTheEvent();
    }

    private void listenTheEvent() {

    }

    private void initViews() {
        customView = findViewById(R.id.customView);
        tvTest = findViewById(R.id.tvTest);
        customView.setMyCallBack(isEnd -> {
            if (isEnd){
                Toast.makeText(MainActivity.this, "emd", Toast.LENGTH_SHORT).show();
            }
        });


    }

}