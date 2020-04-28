package com.example.lab5_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            for (SmsMessage smsg : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String msg = "From " + smsg.getOriginatingAddress() + " : " + smsg.getMessageBody();
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
