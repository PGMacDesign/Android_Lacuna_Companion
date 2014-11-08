package com.pgmacdesign.lacunacompanion;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefs {

	
	/*
	 * This allows doubles to be entered into the data field.  
	 * IE) sp.putDouble(editor, "sales_dollars", 2.4231);  @Params,
	 * 1) Editor being used 
	 * 2) Which 'column' the data is being entered to
	 * 3) Value To Enter
	 */
	Editor putDouble (final Editor edit, final String key, final double value){
		return edit.putLong(key, Double.doubleToRawLongBits(value));
	}
	
	/*
	 * Returns a double from the shared preferences data field. @Params,
	 * IE) sp.getDouble(settings, "sales_dollars", 0.0);  @Params,
	 * 1) SharedPreferences Variable (Defined in global variables)
	 * 2) Which 'column' the data is being pulled from
	 * 3) A Default value that will be returned if no value exists there
	 */
	double getDouble(final SharedPreferences prefs, final String key, final double defaultValue){
		return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
	}
	
	/*
	 * This allows Integers to be entered into the data field.  
	 * IE) sp.putInt(editor, "zip_code", 90605);  @Params,
	 * 1) Editor being used 
	 * 2) Which 'column' the data is being entered to
	 * 3) Value To Enter
	 */
	Editor putInt (final Editor edit, final String key, final int value){
		return edit.putInt(key, (value));
	}
	
	/*
	 * Returns an int from the shared preferences data field. @Params,
	 * IE) sp.getInt(settings, "zip_code", 00000);  @Params,
	 * 1) SharedPreferences Variable (Defined in global variables)
	 * 2) Which 'column' the data is being pulled from
	 * 3) A Default value that will be returned if no value exists there
	 */
	double getInt(final SharedPreferences prefs, final String key, final int defaultValue){
		return prefs.getInt(key, (defaultValue));
	}	
	
	/*
	 * This allows Strings to be entered into the data field.  
	 * IE) sp.putString(editor, "work_location_home", "1401 W Imperial Hwy, La Habra, CA 90631");  @Params,
	 * 1) Editor being used 
	 * 2) Which 'column' the data is being entered to
	 * 3) Value To Enter
	 */
	Editor putString (final Editor edit, final String key, final String value){
		return edit.putString(key, value);
	}
	
	/*
	 * Returns a String from the shared preferences data field. @Params,
	 * IE) sp.getString(settings, "work_location_home", "Work");  @Params,
	 * 1) SharedPreferences Variable (Defined in global variables)
	 * 2) Which 'column' the data is being pulled from
	 * 3) A Default value that will be returned if no value exists there
	 */
	String getString(final SharedPreferences prefs, final String key, final String defaultValue){
		return prefs.getString(key, defaultValue);
	}
	
	
}
