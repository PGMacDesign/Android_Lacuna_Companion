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
package com.pgmacdesign.lacunacompanion.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pgmacdesign.lacunacompanion.R;
import com.pgmacdesign.lacunacompanion.client.ClientSide;
import com.pgmacdesign.lacunacompanion.client.JsonParser;
import com.pgmacdesign.lacunacompanion.module.inbox.Mail;
import com.pgmacdesign.lacunacompanion.module.inbox.ViewMail;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

public class MailFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, 
            ViewGroup container, Bundle savedInstanceState) {

 	        // Inflate the layout for this fragment
 	        View view =  inflater.inflate(R.layout.view_mail, container, false);
 	        return view;
 	   	}
	
    
    
    
    //
	public void buttonClicked (View view) {
		  
	}


}
