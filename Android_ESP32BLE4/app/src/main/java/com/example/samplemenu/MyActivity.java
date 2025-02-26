package com.example.samplemenu;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class MyActivity extends AppCompatActivity {

//    private FirebaseAnalytics mFirebaseAnalytics;
    TextView bluetoothstatus, bluetoothPaired;
    Button blescan,enableLedButton, btndisconnect, btnshut;
    BluetoothAdapter myBluetooth;
    boolean status;
    ArrayList<String> devicesList;
    ArrayList<BluetoothDevice> ListDevices;
    ArrayAdapter<String> adapter;
    InputStream taInput;
    OutputStream taOut;
    SeekBar seekLED1, seekLED2, seekLED3;
    BluetoothDevice pairedBluetoothDevice = null;
    BluetoothSocket blsocket = null ;


    ListView listt;
    private Context ctx;

    private BluetoothLeService mBluetoothLeService;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   //sean private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //sean_0222
        blescan = (Button) findViewById(R.id.buttonlightup);
        bluetoothstatus = (TextView) findViewById(R.id.bluetooth_state);
        bluetoothPaired = (TextView) findViewById(R.id.bluetooth_paired);
        enableLedButton = (Button) findViewById(R.id.buttonlightup);
        btnshut = (Button) findViewById(R.id.buttonShut);
        btndisconnect = (Button) findViewById(R.id.buttondisconnect);
        listt = (ListView) findViewById(R.id.mylist);
        seekLED1 = (SeekBar) findViewById(R.id.seekled1);
        seekLED2 = (SeekBar) findViewById(R.id.seekled2);
        seekLED3 = (SeekBar) findViewById(R.id.seekled3);

        seekLED1.setMax(25);
        seekLED2.setMax(25);
        seekLED3.setMax(25);


        ListDevices = new ArrayList<BluetoothDevice>();
        devicesList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listitem, R.id.txtlist,  devicesList);
        listt.setAdapter(adapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //sean client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        blescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectScan();

            }
        });



        btnshut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blsocket != null && blsocket.isConnected())
                {

                       send2Bluetooth(13, 13);

                }
            }
        });

        enableLedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blsocket != null && blsocket.isConnected())
                {

                    send2Bluetooth(44, 45);

                }
            }
        });


        btndisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blsocket != null && blsocket.isConnected())
                {
                    try
                    {
                        blsocket.close();
                        Toast.makeText(getApplicationContext(), "disconnected", Toast.LENGTH_LONG).show();
                        bluetoothPaired.setText("DISCONNECTED");
                       // bluetoothPaired.setTextColor(getResources().getColor(R.color.red));

                    }catch (IOException ioe)
                    {
                        Log.e("app>", "Cannot close socket");
                        pairedBluetoothDevice = null;
                        Toast.makeText(getApplicationContext(), "Could not disconnect", Toast.LENGTH_LONG).show();

                    }

                }
            }
        });

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy

        listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "item with address: " + devicesList.get(i) + " clicked", Toast.LENGTH_LONG).show();

            connect2LED(ListDevices.get(i));
            }
        });



        // getting seekbar current value

        seekLED1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int currentVal = 0 ;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                currentVal = i ;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                send2Bluetooth ( 100, currentVal );


                Toast.makeText(getApplicationContext(), "LED 1 : "+ currentVal, Toast.LENGTH_SHORT).show();
            }
        });
        seekLED2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int currentVal = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                currentVal = i ;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "LED 2 : "+ currentVal, Toast.LENGTH_SHORT).show();

                send2Bluetooth ( 200, currentVal );
            }
        });

        seekLED3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int currentVal = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentVal = i ;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                send2Bluetooth ( 0, currentVal );
                Toast.makeText(getApplicationContext(), "LED 3 : "+ currentVal, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //sean client.connect();

        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        status = myBluetooth.isEnabled();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
       // myBluetooth.startDiscovery();
        if (status)
        {
            bluetoothstatus.setText("ENABLED");
            registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        }
        else {
            bluetoothstatus.setText("NOT READY");
        }
    }

    void connectScan(){
        //mBluetoothLeService.connect(mDeviceAddress);

    }
    void connect2LED(BluetoothDevice device)
    {
         UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb") ;
//        try {
//            //sean blsocket = device.createInsecureRfcommSocketToServiceRecord(uuid);
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
////            blsocket = device.createInsecureRfcommSocketToServiceRecord(uuid);
////            blsocket.connect();
//            pairedBluetoothDevice = device;
////            bluetoothPaired.setText("PAIRED: "+device.getName());
////            bluetoothPaired.setTextColor(getResources().getColor(R.color.green));
//
//            Toast.makeText(getApplicationContext(), "Device paired successfully!",Toast.LENGTH_LONG).show();
//        }catch(IOException ioe)
//        {
//            Log.e("taha>", "cannot connect to device :( " +ioe);
//            Toast.makeText(getApplicationContext(), "Could not connect",Toast.LENGTH_LONG).show();
//            pairedBluetoothDevice = null;
//        }
    }

    void send2Bluetooth(int led, int brightness)
    {
        //make sure there is a paired device
        if ( pairedBluetoothDevice != null && blsocket != null )
        {
             try
             {
                 taOut = blsocket.getOutputStream();
                 taOut.write(led + brightness);

                 taOut.flush();
             }catch(IOException ioe)
             {
                 Log.e( "app>" , "Could not open a output stream "+ ioe );
             }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
   @Override
   protected void onResume() {
       super.onResume();

       // Log an event with Firebase Analytics
       Bundle params = new Bundle();
       params.putString("page_name", "home_page");
//       mFirebaseAnalytics.logEvent("page_view", params);
   }
    private void createDynamicLink() {
//        FirebaseDynamicLinks.getInstance()
//                .createDynamicLink()
//                .setLink(Uri.parse("https://www.example.com"))
//                .setDomainUriPrefix("https://example.page.link")
//                .buildDynamicLink();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information

//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "My Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.hishri.fnarduino/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {

        public void onReceive(Context context, Intent intent)
        {

            Log.i("app>", "broadcast received") ;
            String action = intent.getAction();
            //sean
             ctx=context;

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                if (ActivityCompat.checkSelfPermission(ctx, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
//                devicesList.add(device.getName() + " @"+device.getAddress());
                ListDevices.add(device);

                adapter.notifyDataSetChanged();
            }
        }
    };

}


