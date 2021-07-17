package com.gacha.receiveapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.gacha.receiveapplication.receive.SMSReceiver;

public class MainActivity extends AppCompatActivity {

    private final int REQ_CODE = 1;
    private SMSReceiver smsReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!checkPermission(Manifest.permission.RECEIVE_SMS) || !checkPermission(Manifest.permission.CALL_PHONE)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.CALL_PHONE}, REQ_CODE);
            }
        } else {
            initialization();
        }
    }

    private boolean checkPermission(String permission) {
        int check = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            check = checkSelfPermission(permission);
        }
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    // Processing the user permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                initialization();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermission(Manifest.permission.RECEIVE_SMS) && checkPermission(Manifest.permission.CALL_PHONE)) {
            registerReceiver(smsReceiver, filter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initialization() {
        smsReceiver = new SMSReceiver();
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, filter);
    }
}