package com.pgmacdesign.lacunacompanion.client;

import org.json.JSONObject;

import com.pgmacdesign.lacunacompanion.L;
import com.pgmacdesign.lacunacompanion.MainActivity;
import com.pgmacdesign.lacunacompanion.R;
import com.pgmacdesign.lacunacompanion.SharedPrefs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Login extends Activity {

	//Shared Preferences
	public static final String PREFS_NAME = "LacunaCompanion";
	SharedPrefs sp = new SharedPrefs();
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	
	//EditTexts
	EditText empireNameField, passWordField;
	
	//Checkbox
	private CheckBox saveLoginCheckBox;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Initialize();
		
		Button loginButton = (Button) findViewById(R.id.loginButton);
		
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Get entered empire name and password
				String empireName = empireNameField.getText().toString();
				String empirePassword = passWordField.getText().toString();

				// Get selected item from Spinner
				Spinner selectServerSpinner = (Spinner) findViewById(R.id.selectServer);
				int indexValue = selectServerSpinner.getSelectedItemPosition();

				if (empireName.length() <= 0 || empireName == "") {
					Toast.makeText(Login.this,"Please enter your empire name.",Toast.LENGTH_SHORT).show();
				}
				else if (empirePassword.length() <= 0 || empirePassword == "") {
					Toast.makeText(Login.this,"Please enter your empire password.",Toast.LENGTH_SHORT).show();
				}
				else {
					//Set selectedServer and apiKey based on selected item in Spinner. Server defaults to US1.
					String selectedServer = null;
					String apiKey = null;
					if (indexValue == 0) {
						//Toast.makeText(Login.this,"Please select a server.",Toast.LENGTH_SHORT).show();
						selectedServer = "us1";
						apiKey = "01420b89-22d4-437f-b355-b99df1f4c8ea";
					}
					else if (indexValue == 1) {
						selectedServer = "us1";
						apiKey = "01420b89-22d4-437f-b355-b99df1f4c8ea";
					}
					else if (indexValue == 2) {
						selectedServer = "pt";
						apiKey = "a6f619a8-1cd7-429b-8fbf-83ede625612c";
					}
					else {
						selectedServer = "us1";
						apiKey = "01420b89-22d4-437f-b355-b99df1f4c8ea";
					}
					
					String SELECTED_SERVER = selectedServer; //removed final label so I could use in checkbox boolean
					final String API_KEY = apiKey;

					// Doing this stops it from crashing randomly when no server is selected.
					if (selectedServer != null && apiKey != null) {
						
						// Initialize the Client class.
						ClientSide.setContext(Login.this);
						
						////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						//Write here to save to shared preferences if they select the checkbox
						if (saveLoginCheckBox.isChecked()) {
							//Put the selected info into shared preferences
							sp.putString(editor, "remember_selected_server", SELECTED_SERVER);
							sp.putString(editor, "remember_empire_name", empireName);
							sp.putString(editor, "remember_empire_password", empirePassword);
							sp.putBoolean(editor, "remember_me", true);
							editor.commit(); //Commits changes
						} else {
							sp.putString(editor, "remember_selected_server", "");
							sp.putString(editor, "remember_empire_name", "");
							sp.putString(editor, "remember_empire_password", "");
							sp.putBoolean(editor, "remember_me", false);
							editor.commit(); //Commits changes
						}		

						ClientSide.login(empireName, empirePassword, SELECTED_SERVER, API_KEY);
						
						
						//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
						//ClientSide.login(empireName, empirePassword, SELECTED_SERVER, API_KEY);
						JSONObject status   = ClientSide.STATUS;
						
						// Make sure that we don't run into a NullPointerException.
						if (status != null) {
							// Get the home planet id.
							JSONObject empire   = JsonParser.getJO(status, "empire");
							String homePlanetId = JsonParser.getS(empire, "home_planet_id");
							
							L.m("Home Planet ID",homePlanetId);
							
							Intent intent = new Intent (Login.this, MainActivity.class);
							startActivity(intent);
							finish();
							
							/*
							Intent intent = new Intent(Login.this,PlanetResourceView.class);
							intent.putExtra("planetId", homePlanetId);

							Login.this.startActivity(intent);
							finish();
							*/
						}
					}
				}
			}
		});
	}

	private void Initialize() {
		//Shared Preferences Stuff
		settings = getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		
		empireNameField = (EditText) findViewById(R.id.empireNameField);
		passWordField = (EditText) findViewById(R.id.passWordField);
		saveLoginCheckBox = (CheckBox) findViewById(R.id.remember_me_login);
		
		//If statement to cover remember me
		if (sp.getBoolean(settings, "remember_me", false) == true ){
			//Set text to the saved information
			empireNameField.setText(sp.getString(settings, "remember_empire_name", null));
			passWordField.setText(sp.getString(settings, "remember_empire_password", null));
			saveLoginCheckBox.setChecked(true);
			L.m("Remember Me","Fields have been filled");			
		} else {
			//Set text to nothing
		}
	}
}
