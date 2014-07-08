package com.classycoder;

import java.util.Calendar;
import java.util.TimeZone;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
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
import android.os.Build;
import android.telephony.*;

public class MainActivity extends ActionBarActivity {

	@Override
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
		Calendar midnightCalendar = Calendar.getInstance();
//		midnightCalendar.setTimeZone(TimeZone.getTimeZone("CT"));
		midnightCalendar.set(Calendar.HOUR_OF_DAY, 22);
		midnightCalendar.set(Calendar.MINUTE, 37);
		midnightCalendar.set(Calendar.SECOND, 0);
		
		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		/*Class which sends the SMS and receives the alarm*/
		Intent smsSender = new Intent("com.classycoder.AlarmReceiver");
		/* Creates a pending intent to be called at midnight */
		PendingIntent tester = PendingIntent.getBroadcast(this, 0, smsSender, PendingIntent.FLAG_CANCEL_CURRENT);
		alarm.cancel(tester);
		alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, midnightCalendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, tester);
		Toast.makeText(getApplicationContext(), "You have sent a message to your friend!", Toast.LENGTH_SHORT).show();

	}

	public void spamFriend(View view){
		EditText editSpamFriend = (EditText) findViewById(R.id.editSpamFriend);
		String num = editSpamFriend.getText().toString();
		
		/* If number no length 11, then throw a message */
		if(!PhoneNumberUtils.isGlobalPhoneNumber(num)){
			Toast.makeText(getApplicationContext(), "Invalid number, please try again.", Toast.LENGTH_SHORT).show();
		}
		
		else{
			SmsManager messenger = android.telephony.SmsManager.getDefault();
			messenger.sendTextMessage(num, null, "hello!", null, null);
			Toast.makeText(getApplicationContext(), "You have sent a message to your friend!", Toast.LENGTH_SHORT).show();
		}
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
		if (id == R.id.action_settings) {
			return true;
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
