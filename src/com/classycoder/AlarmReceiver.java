package com.classycoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.telephony.SmsManager;

public class AlarmReceiver extends BroadcastReceiver {

	/* At certain time of the day, send the text message */ 
	public void onReceive(Context context, Intent intent) {
		/* Receive the number from another intent */
		String num = intent.getStringExtra("phoneNum");
		String message = getMessage(context);		
		
		/* Parcel read error so need to fix null exception */
		if(num != null && message != null)
			sendSms(num, message);
	}

	/* Method to return a string */
	private String getMessage(Context context) {
		AssetManager manager = context.getAssets();
		ArrayList<String> all_txt = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(manager.open("lovelypuns.txt")));
			
			String line; 
			while ((line = br.readLine()) != null){
				all_txt.add(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int len = all_txt.size();
		Random rand = new Random();
		
		/* Pick a random message from the file */
		return all_txt.get(rand.nextInt(len));
	}

	/* Method to fire a text */
	private void sendSms(String num, String message){
		/* Send the text */
		message += "\n-The Classy Coder";
		SmsManager messenger = android.telephony.SmsManager.getDefault();
		messenger.sendTextMessage(num, null, message, null, null);
	}

}