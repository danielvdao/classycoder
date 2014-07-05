package com.classycoder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String phoneNum ="";
		String msg = "";
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNum, null, msg, null, null);
	}

}
