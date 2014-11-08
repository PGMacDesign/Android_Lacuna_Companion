/*
        Copyright (C) <2014>  <Patrick Gray MacDowell>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.pgmacdesign.lacunacompanion;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class L {

	public static void m(String message){
		Log.d("Log", message);
	}
	
	public static void m(String tag, String message){
		Log.d(tag, message);
	}
	
	public static void makeToast(Context context, String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
