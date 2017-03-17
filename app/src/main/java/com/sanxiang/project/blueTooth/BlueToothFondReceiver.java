package com.sanxiang.project.blueTooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2017/2/17.
 */
public class BlueToothFondReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if(BluetoothDevice.ACTION_FOUND.equals(action)){
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            
            Log.e("---------",device.getName()+"-------"+device.getAddress());
        }

    }
}
