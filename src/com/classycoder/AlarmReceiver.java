package com.classycoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	
	/* At certain time of the day, send the text message */ 
	public void onReceive(Context context, Intent intent) {
		/* Receive the number from another intent */
		String num = intent.getStringExtra(MainActivity.PHONE_NUMBER);
		String message = intent.getStringExtra(MainActivity.TEXT_BODY);
		sendSms(num, message);
	}
	
	/* Method to fire a text */
	private void sendSms(String num, String message){
		/* Send the text */
		SmsManager messenger = android.telephony.SmsManager.getDefault();
		messenger.sendTextMessage(num, null, message, null, null);
	}
	
}
