package com.classycoder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	
	/* At certain time of the day, send the text message */ 
	public void onReceive(Context context, Intent intent) {
		String phoneNum ="8322739257";
//		String msg = "hello this is your daily text message!";
		SmsManager sms = android.telephony.SmsManager.getDefault();
		sms.sendTextMessage(phoneNum, null, "hello this is daily", null, null);
	}

}
