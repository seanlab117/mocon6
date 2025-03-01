package com.example.samplemenu;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hishri.fnarduino.R;

public class MainActivity9 extends AppCompatActivity {

    public static final int ESP32_PORT = 80;

    private String esp32IpAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        esp32IpAddress = getIntent().getStringExtra("IP_ADDRESS");

        Button buttonLed01 = findViewById(R.id.buttonLed01);
        //sean Led01 ledControl = new Led01(buttonLed01, esp32IpAddress);

        Button buttonLed02 = findViewById(R.id.buttonLed02);
        //sean Led02 ledControl02 = new Led02(buttonLed02, esp32IpAddress);

        Button buttonLed03 = findViewById(R.id.buttonLed03);
        //sean Led03 ledControl03 = new Led03(buttonLed03, esp32IpAddress);

    }
}