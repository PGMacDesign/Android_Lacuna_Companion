package com.pgmacdesign.lacunacompanion;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {

MediaPlayer ourIntroSong, doorClose; //Doorclose not used at this point, will setup for transitional sound at some point
	
	@Override
	protected void onCreate(Bundle inputVariableToSendToSuperClass) {

		super.onCreate(inputVariableToSendToSuperClass);
		setContentView(R.layout.splash);

		/*
		//Remember: Sound pool used for small clips (gun, explosion, etc.) and Media player used for larger clips (background music)
		ourIntroSong = MediaPlayer.create(Splash.this, R.raw.cinematic_impact);
		MediaPlayer doorClose = MediaPlayer.create(SplashScreen.this, R.raw.door_close_1);
		
		//getPrefs variable to determine if they have turned off the music preference in the introduction
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkBox", true);
		if (music == true){
			ourIntroSong.start();
		}
	
		 */
		//Determines length of time splash screen is open
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					//In Milliseconds, set to 3 seconds at this point
					sleep(3000);
				} catch (InterruptedException e01) {
					String error_in_splash = e01.toString(); //For Debugging purposes
					e01.printStackTrace();
				} finally {
					Intent openMain = new Intent(Splash.this, MainActivity.class);
					startActivity(openMain);
				}
			}
		};

		timer.start();
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		//This kills the music so it isn't carried over between splash screens
		//ourIntroSong.release();
		
		//Destroys the class when it goes on pause. Not ideal for most programs, but, for a splash screen, this works fine as we don't want it to show up again. 
		finish(); 
	}	
}