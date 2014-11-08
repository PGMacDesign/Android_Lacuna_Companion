package com.pgmacdesign.lacunacompanion;

import java.io.IOException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
	
	/*
	
	Order of the Slider Nav bar:
	0) Home
	1) Log out / in
	2) Mail (Add a still under construction notice to this)
	3) Upgrade / Build queue
	4) List of planets and resources per hour
	5) Web Links (IE the wiki)
	6) Ships Info
	7) Building Info
	8) Lookup Planet information
	9) Orbit (x, y) Calculator
	10) Distance Calculator
	11) Sitter PW Manager
	12) Settings / Preferences
	13) About us / Credits (Don't forget Vas!)
	14) Donate
	
	
	Extras to add at some point
	1) Notifications if a planet drops to negative happiness (IE notification bar)
	2) Mail in the correspondence tab (What people look for anyway)
	 */
	
	
	//Shared Preferences
	public static final String PREFS_NAME = "RSRToolboxData";	
	SharedPrefs sp = new SharedPrefs();
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	
	//Main - When the activity starts
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Initialize Variables
		Initialize();
		
		
	}

	//Initialize Variables
	private void Initialize(){
		
		//Shared Preferences Stuff
		settings = getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
	}
	
	//On Click Method
	public void onClick(View arg0) {
		/*
		switch (arg0.getId()){
		
		case R.id.button_ID_That_Was_Clicked:
			
			break;
			
		case R.id.button_ID_That_Was_Clicked:
			
			break;
			
		}
		*/
	}
	
	protected void onPause() {

		super.onPause();
		finish();
	}


		

	
}
