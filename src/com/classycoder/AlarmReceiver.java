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
		String num = intent.getStringExtra("phoneNum");
		String message = getTextBody(context, Calendar.AM_PM);	
		
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
	
	/* Method to return the text message that needs to be sent */
	private String getTextBody(Context context, int time_of_day){
		ArrayList<String> all_txt = new ArrayList<String>();
		try{
			BufferedReader br;
			
			/* Morning messages */
			if(time_of_day == 0){
				br = new BufferedReader(new InputStreamReader(context.getAssets().open("mornings.txt")));
			}
			
			/* Afternoon messages */
			else{
				br = new BufferedReader(new InputStreamReader(context.getAssets().open("afternoons.txt")));
			}
			
			String line; 
			while ((line = br.readLine()) != null){
				all_txt.add(line);
			}
		}
		/* Error message handling */
		catch (IOException e){
			Toast.makeText(context.getApplicationContext(), "Text body cannot be sent.", Toast.LENGTH_SHORT).show();
		}
		
		int len = all_txt.size();
		Random rand = new Random();
		
		/* Pick a random message from the file */
		return all_txt.get(rand.nextInt(len));
	}
	
}
