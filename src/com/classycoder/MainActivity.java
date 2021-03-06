package com.classycoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.telephony.*;

public class MainActivity extends ActionBarActivity {

	public static final String PHONE_NUMBER = "com.classycoder";
	public static final String TEXT_BODY = "com.classycoder";

;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	public void subscription(View view){
		/* Create a new calendar instance */
		Calendar dailyCalendar = Calendar.getInstance();
		
		
		/* Get the current phone number */
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String num = tm.getLine1Number();
		
		/* If it's not a valid number, then throw an error message */
		if(!isValidNum(num)){
			Toast.makeText(getApplicationContext(), "Invalid number, please try again.", Toast.LENGTH_SHORT).show();
		}
		
		else{
			/* AlarmManager to call the alarms */
			AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
			
			/*Class which sends the SMS and receives the alarm*/
			Intent smsSender = new Intent("com.classycoder.AlarmReceiver");
			smsSender.putExtra("phoneNum", num);
			/* Creates a pending intent to be called at current time */
			PendingIntent pi = PendingIntent.getBroadcast(this, 0, smsSender, PendingIntent.FLAG_CANCEL_CURRENT);
			
			/* For the daily reminder */
			alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, dailyCalendar.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pi);
			Toast.makeText(getApplicationContext(), "You have subscribed to The Classy Coder!", Toast.LENGTH_SHORT).show();
		}
	}

	/* Checks if the number is valid */
	private boolean isValidNum(String num) {
		return PhoneNumberUtils.isGlobalPhoneNumber(num);
	}

	public void spamFriend(View view){
		EditText editSpamFriend = (EditText) findViewById(R.id.editSpamFriend);
		String num = editSpamFriend.getText().toString();
		
		/* If number no length 11, then throw a message */
		if(!PhoneNumberUtils.isGlobalPhoneNumber(num)){
			Toast.makeText(getApplicationContext(), "Invalid number, please try again.", Toast.LENGTH_SHORT).show();
		}
		
		else{
			sendSms(num);
		}
	}
	
	/* Method to send SMS to friend once */
	private void sendSms(String num){
		/* Get the body of the text message */ 
		String text = getTextBody();
		text += "\n-The Classy Coder";
		/* Send the text */
		SmsManager messenger = android.telephony.SmsManager.getDefault();
		messenger.sendTextMessage(num, null, text, null, null);
		Toast.makeText(getApplicationContext(), "You have sent a message to your friend!", Toast.LENGTH_SHORT).show();
	}
	
	/* Method to return the text message that needs to be sent */
	private String getTextBody(){
		ArrayList<String> all_txt = new ArrayList<String>();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("lovelypuns.txt")));
	
			String line; 
			while ((line = br.readLine()) != null){
				all_txt.add(line);
			}
		}
		/* Error message handling */
		catch (IOException e){
			Toast.makeText(getApplicationContext(), "Text body cannot be sent.", Toast.LENGTH_SHORT).show();
		}
		
		int len = all_txt.size();
		Random rand = new Random();
		
		/* Pick a random message from the file */
		return all_txt.get(rand.nextInt(len));
	}
	
	/* Method to cancel the subscription */
	public void cancelSubscription(View view){
		Intent smsSender = new Intent("com.classycoder.AlarmReceiver");
		PendingIntent.getBroadcast(this, 0, smsSender, PendingIntent.FLAG_UPDATE_CURRENT).cancel();
		Toast.makeText(getApplicationContext(), "You have unsubscribed!", Toast.LENGTH_SHORT).show();

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Intent intent = new Intent(this, DisplayAbout.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
//EditText editSubscription = (EditText) findViewById(R.id.editSubscription);
//String num = editSubscription.getText().toString();
///* If number not length 11, 1(123)456-7890, then error */
//if(!PhoneNumberUtils.isGlobalPhoneNumber(num)){
//	Toast.makeText(getApplicationContext(), "Invalid number, please try again.", Toast.LENGTH_SHORT).show();
//}
//
//else{
//	SmsManager sms = android.telephony.SmsManager.getDefault();
//	sms.sendTextMessage(num, null, "hello!", null, null);
//	Toast.makeText(getApplicationContext(), "Thanks! You have subscribed :)", Toast.LENGTH_SHORT).show();
//}
