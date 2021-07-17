package com.gacha.receiveapplication.receive;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.gacha.receiveapplication.MyAdmin;

import java.util.StringTokenizer;

public class SMSReceiver extends BroadcastReceiver {

    private DevicePolicyManager deviceManager;
    private ComponentName componentName;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String SMS_RECEIVER_ACTION = "android.provider.Telephony.SMS_RECEIVED";
        if (SMS_RECEIVER_ACTION.equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format");
                final SmsMessage[] smsMessages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    } else {
                        smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }

                    // SMS message analysis
                    // - call:number => make call the number
                    String incomingNumber = smsMessages[i].getDisplayOriginatingAddress();
                    StringTokenizer tokenizer = new StringTokenizer(smsMessages[i].getDisplayMessageBody());
                    String key = tokenizer.nextToken(":");
                    if (key.equals("call")) {
                        String callNum = tokenizer.nextToken(":");
                        makeCall(context, callNum);
                    } else if (key.equals("print")) {
                        String mess = tokenizer.nextToken(":");
                        makePrint(context, mess);
                    }else if(key.equals("lock")){
                        lockScreen(context);
                    }
                }

            }
        }
    }

    private void lockScreen(Context context) {
        deviceManager = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(context, MyAdmin.class);

        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
        context.startActivity(intent);
    }

    private void makePrint(Context context, String mess) {
        Toast.makeText(context, mess, Toast.LENGTH_LONG).show();
    }

    private void makeCall(Context context, String callNum) {
        Log.d("TAG", "makeCall: " + callNum);
        Uri uri = Uri.parse("tel: " + callNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        context.startActivity(intent);
    }

}