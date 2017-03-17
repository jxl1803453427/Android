package com.sanxiang.project.blueTooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sanxiang.project.R;
import com.sanxiang.project.util.MyLog3;

import java.util.Set;
import java.util.UUID;

public class BlueToothActivity extends AppCompatActivity {
    private String UUID = "11223344";
    public static BluetoothSocket bluetoothSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        IntentFilter fondFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        IntentFilter startFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        IntentFilter endFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(new BlueToothFondReceiver(),fondFilter);
        registerReceiver(new BlueToothStartReceiver(),startFilter);
        registerReceiver(new BlueToothFinishReceiver(),endFilter);


        adapter = BluetoothAdapter.getDefaultAdapter();
    }
    private BluetoothAdapter adapter;
    public void open(View view){

        if(adapter == null){
            MyLog3.log("该设备无蓝牙！");
            return;
        }

        if(adapter.isEnabled()){
            Toast.makeText(this,"蓝牙已打开!",Toast.LENGTH_SHORT).show();
            return;
        }
//        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//        startActivityForResult(intent,1);

        adapter.enable();

    }

    public void get(View view){
        if(adapter == null){
            MyLog3.log("该设备无蓝牙！");
            return;
        }
        Set<BluetoothDevice> set = adapter.getBondedDevices();
        for (BluetoothDevice device:set){
            MyLog3.log(device.getName());
        }
    }
    public void reset(View view){
        Intent discoverIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
        startActivity(discoverIntent);//使蓝牙可见，若已可见则重新计时，有些蓝牙没有此，即若打开一直可见

    }
    public void discover(View view){

        adapter.startDiscovery();

    }
    public void cancle(View view){

        adapter.cancelDiscovery();


    }
    public void close(View view){
        if(adapter == null){
            MyLog3.log("该设备无蓝牙！");
            return;
        }
        if(adapter.isEnabled()){
            adapter.disable();
        }

    }


}
