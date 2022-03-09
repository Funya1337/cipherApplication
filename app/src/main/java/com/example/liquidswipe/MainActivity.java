package com.example.liquidswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.cuberto.liquid_swipe.LiquidPager;

public class MainActivity extends AppCompatActivity {

    LiquidPager pager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pager = findViewById(R.id.pager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), 1);

        pager.setAdapter(adapter);
    }
}