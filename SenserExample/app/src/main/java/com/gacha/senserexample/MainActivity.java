package com.gacha.senserexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private EditText edt;
    private DevicePolicyManager deviceManager;
    private ComponentName componentName;

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] sensorData = event.values;
            float x = sensorData[0];
            float y = sensorData[1];
            float z = sensorData[2];

            float g = SensorManager.GRAVITY_EARTH;
            float root = (x*x + y*y + z*z)/(g*g);
            edt.setText(root+"");

            if (root>2){
                boolean active = deviceManager.isAdminActive(componentName);
                if (active){
                    deviceManager.lockNow();
                }else{
                    Toast.makeText(MainActivity.this,"this admin permission is not activated",Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        deviceManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this,MyAdmin.class);

        setContentView(R.layout.activity_main);

        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
        startActivity(intent);

        data = new ArrayList<>();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //get the accelerometer
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ListView listView = findViewById(R.id.listView);
        edt = findViewById(R.id.edt);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSensor.equals(null)){
            Toast.makeText(this,"your phone is not supported this sensor!",Toast.LENGTH_SHORT).show();
        }else{
            mSensorManager.registerListener(sensorEventListener,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSensor.equals(null)){
            mSensorManager.unregisterListener(sensorEventListener,mSensor);
        }else{
            mSensorManager.registerListener(sensorEventListener,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}