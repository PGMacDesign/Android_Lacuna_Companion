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

//Many Thanks to Ravi Tamada of AndroidHive for his amazing tutorials!
package com.pgmacdesign.lacunacompanion.adapters;


import java.util.ArrayList;

import com.pgmacdesign.lacunacompanion.R;
import com.pgmacdesign.lacunacompanion.slidingmenu.NavDrawerItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter {
    
   private Context context;
   private ArrayList<NavDrawerItem> navDrawerItems;
    
   public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
       this.context = context;
       this.navDrawerItems = navDrawerItems;
   }

   @Override
   public int getCount() {
       return navDrawerItems.size();
   }

   @Override
   public Object getItem(int position) {       
       return navDrawerItems.get(position);
   }

   @Override
   public long getItemId(int position) {
       return position;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null) {
           LayoutInflater mInflater = (LayoutInflater)
                   context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
           convertView = mInflater.inflate(R.layout.drawer_list_item, null);
       }
         
       ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
       TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
       TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
         
       imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
       txtTitle.setText(navDrawerItems.get(position).getTitle());
        
       // displaying count
       // check whether it set visible or not
       if(navDrawerItems.get(position).getCounterVisibility()){
           txtCount.setText(navDrawerItems.get(position).getCount());
       }else{
           // hide the counter view
           txtCount.setVisibility(View.GONE);
       }
        
       return convertView;
   }

}