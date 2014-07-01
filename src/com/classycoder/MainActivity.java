package com.classycoder;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
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
		EditText editSubscription = (EditText) findViewById(R.id.editSubscription);
		String num = editSubscription.getText().toString();
		num = PhoneNumberUtils.formatNumber(num);
		/* If number not length 11, 1(123)456-7890, then error */
		if(checkNum(num) == false || num.length() != 11){
			Toast.makeText(getApplicationContext(), "Invalid number, please try again.", Toast.LENGTH_SHORT).show();
		}
		
		else{
			Toast.makeText(getApplicationContext(), "Thanks! You have subscribed :)", Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean checkNum(String num) {
		// TODO Auto-generated method stub
		return PhoneNumberUtils.isGlobalPhoneNumber(num);
	}

	public void spamFriend(View view){
		EditText editSpamFriend = (EditText) findViewById(R.id.editSpamFriend);
		String num = editSpamFriend.getText().toString();
		num = PhoneNumberUtils.formatNumber(num);

		/* If number no length 11, then throw a message */
		if(checkNum(num) == false || num.length() != 11){
			Toast.makeText(getApplicationContext(), "Invalid number, please try again.", Toast.LENGTH_SHORT).show();
		}
		
		else{
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
