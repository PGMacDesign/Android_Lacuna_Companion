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
