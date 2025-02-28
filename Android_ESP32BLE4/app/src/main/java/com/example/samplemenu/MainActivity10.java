package com.example.samplemenu;



import static android.os.SystemClock.sleep;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity10 extends AppCompatActivity {

    private WifiManager wifiManager;

    private ArrayAdapter<String> adapter;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        progressDialog = new ProgressDialog(MainActivity10.this);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);


        //this code for list the available wifi list
        ListView wifiList = findViewById(R.id.wifiList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        wifiList.setAdapter(adapter);
        //sean
        wifiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String selectedItem = items.get(position);
               // String selectedItem=items.get(position);
                Log.d("haha", "onItemClick:");
                check(position);
                //Toast.makeText(MainActivity10.this, "Clicked: " + Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity10.this, "onItemClick wifi", Toast.LENGTH_LONG).show();
            }
        });


        Log.d("haha", "setOnItemClickListener:");


        Button buttonScan = findViewById(R.id.scanBtn);

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(MainActivity10.this, "Turn on your wifi", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        registerReceiver(wifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("haha", "setOnClickListener:");
                progressDialog.setMessage("Scanning...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                checkLocationPermission();
            }
        });
        //sean






    }



    // BroadcastReceiver to receive WiFi scan results
    private final BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                Log.d("haha", "BroadcastReceiver:");

                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Log.d("haha", "checkSelfPermission:");
                    return;
                }
                Log.d("haha", "BroadcastReceiver2 xxx :");
                List<ScanResult> scanResults = wifiManager.getScanResults();
                Log.d("haha", "BroadcastReceiver3 xxx:");
                // Clear previous results
                adapter.clear();

                // Display the WiFi networks in the ListView
                Log.d("haha", " WiFi networks:"+scanResults);
                for (ScanResult result : scanResults) {

                    //String wifiInfo = result.SSID + " (" + result.capabilities + ")";

                    String wifiInfo = result.SSID;

                    Toast.makeText(MainActivity10.this, wifiInfo, Toast.LENGTH_LONG).show();

                    adapter.add(wifiInfo);

                    //sean



                    Log.d("haha", "adapter.add:"+wifiInfo);
                }

                // Notify the adapter that the data set has changed
                Log.d("haha", "adapter.notifyDataSetChanged 1:");
                adapter.notifyDataSetChanged();

               //sean sleep(5000);


                progressDialog.dismiss();
                // Start a new scan for continuous updates

                wifiManager.startScan();
                Log.d("haha", "wifiManager.startScan:");
            }
        }
    };
    private void check(int position) {
        switch (position) {
            case 0: {

                Intent intent = new Intent(this, MainActivity.class);
                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 0", Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(this, MainActivity.class);
//                intent.setClassName(packageName0, className0);
//                intent.putExtra("challenge", "123456");
//                ActivityResultLauncher.launch(intent);
            }
            break;
            case 1: {
                Intent intent = new Intent(this, MainActivity.class);
                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 1", Toast.LENGTH_LONG).show();
            }
            break;
            case 2: {
//                Intent intent = new Intent(this, MyActivity.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 2", Toast.LENGTH_LONG).show();
            }
            break;
            case 3: {
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.setClassName(packageName3, className3);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 3", Toast.LENGTH_LONG).show();
            }
            break;
            case 4: {
//                Intent intent = new Intent(this, MainActivity7.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 4", Toast.LENGTH_LONG).show();
            }
            break;
            case 5: {
//                Intent intent = new Intent(this, MainActivity8.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 5", Toast.LENGTH_LONG).show();
            }
            break;
            case 6: {
//                Intent intent = new Intent(this, MainActivity2.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 6", Toast.LENGTH_LONG).show();
            }
            break;
            default: {
//                Intent intent = new Intent(this, MainActivity10.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position default", Toast.LENGTH_LONG).show();
            }
            break;
        }
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            progressDialog.dismiss();
        } else {
            // Permission already granted, proceed with WiFi scanning
            wifiManager.startScan();
        }
    }
    private final androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
//                        Uri imageUri = data.getData();
//                        imageView.setImageURI(imageUri); // Example: Set image to an ImageView
                        Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_LONG).show();
                    }
                }
            });




    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiScanReceiver);
    }
}