package com.example.samplemenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.samplemenu.ui.main.MainFragment;
import com.hishri.fnarduino.R;

import androidx.fragment.app.FragmentManager;

public class MainActivity2 extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}