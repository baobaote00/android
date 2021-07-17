package com.gacha.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetic;
    private ImageView img;

    private float[] mGravityValues = new float[3];
    private float[] mAccelerationValues = new float[3];
    private float[] mRotationMatrix = new float[9];

    private float mLastDirectionInDegrees = 0f;

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    mAccelerationValues = event.values.clone();
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    mGravityValues = event.values.clone();
                    break;
            }

            boolean success = SensorManager.getRotationMatrix(mRotationMatrix,null,mAccelerationValues,mGravityValues);

            if (success){
                float[] orientationValues = new float[3];
                SensorManager.getOrientation(mRotationMatrix,orientationValues);
                float azimuth = (float) Math.toDegrees(-orientationValues[0]);
                RotateAnimation rotateAnimation = new RotateAnimation(
                        mLastDirectionInDegrees,azimuth,
                        Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f
                );
                rotateAnimation.setDuration(50);
                rotateAnimation.setFillAfter(true);
                img.startAnimation(rotateAnimation);
                mLastDirectionInDegrees = azimuth;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        img = findViewById(R.id.imageView);

        //get the accelerometer
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mSensorMagnetic == null||mSensorAccelerometer==null){
            Toast.makeText(this,"your phone is not supported this sensor!",Toast.LENGTH_SHORT).show();
        }else{
            mSensorManager.registerListener(sensorEventListener,mSensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
            mSensorManager.registerListener(sensorEventListener,mSensorMagnetic,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSensorAccelerometer == null||mSensorMagnetic==null){
            Toast.makeText(this,"your phone is not supported this sensor!",Toast.LENGTH_SHORT).show();
        }else{
            mSensorManager.unregisterListener(sensorEventListener);
        }
    }
}