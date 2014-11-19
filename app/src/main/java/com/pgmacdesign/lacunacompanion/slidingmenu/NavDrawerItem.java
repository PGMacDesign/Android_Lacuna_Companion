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
package com.pgmacdesign.lacunacompanion.slidingmenu;

//Here isCounterVisible defines the visibility of the counter value. If you don’t want to show a counter for a particular list item you can set this to false.
public class NavDrawerItem {

	 private String title;
	    private int icon;
	    private String count = "0";
	    
	    // boolean to set visiblity of the counter
	    private boolean isCounterVisible = false;
	     
	    public NavDrawerItem(){}
	 
	    public NavDrawerItem(String title, int icon){
	        this.title = title;
	        this.icon = icon;
	    }
	     
	    public NavDrawerItem(String title, int icon, boolean isCounterVisible, String count){
	        this.title = title;
	        this.icon = icon;
	        this.isCounterVisible = isCounterVisible;
	        this.count = count;
	    }
	     
	    public String getTitle(){
	        return this.title;
	    }
	     
	    public int getIcon(){
	        return this.icon;
	    }
	     
	    public String getCount(){
	        return this.count;
	    }
	     
	    public boolean getCounterVisibility(){
	        return this.isCounterVisible;
	    }
	     
	    public void setTitle(String title){
	        this.title = title;
	    }
	     
	    public void setIcon(int icon){
	        this.icon = icon;
	    }
	     
	    public void setCount(String count){
	        this.count = count;
	    }
	     
	    public void setCounterVisibility(boolean isCounterVisible){
	        this.isCounterVisible = isCounterVisible;
	    }
	    
}

