package com.classycoder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class AlarmReceiver extends BroadcastReceiver {

	/* At certain time of the day, send the text message */ 
	public void onReceive(Context context, Intent intent) {
		/* Receive the number from another intent */
		String num = intent.getStringExtra("phoneNum");
		String message = intent.getStringExtra("textBody");		

		/* Parcel read error so need to fix null exception */
		if(num != null && message != null)
			sendSms(num, message);
	}

	/* Method to fire a text */
	private void sendSms(String num, String message){
		/* Send the text */
		SmsManager messenger = android.telephony.SmsManager.getDefault();
		messenger.sendTextMessage(num, null, message, null, null);
	}

}