package com.example.samplemenu;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import androidx.core.app.ActivityCompat;

import java.util.List;


public class WiFiConnector {
    private Context ctc;

    public static void connectToWifi(Context context, String ssid, String password) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager == null) return;

        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
            //ctc= context.getApplicationContext();

            WifiConfiguration wifiConfig = new WifiConfiguration();
            wifiConfig.SSID = "\"" + ssid + "\"";
            wifiConfig.preSharedKey = "\"" + password + "\"";

            int netId = wifiManager.addNetwork(wifiConfig);
            if (netId == -1) {
                // Network already exists, find it
                if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                for (WifiConfiguration config : list) {
                    if (config.SSID != null && config.SSID.equals("\"" + ssid + "\"")) {
                        netId = config.networkId;
                        break;
                    }
                }
            }

            if (netId != -1) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();
            }
        }
    }
}
