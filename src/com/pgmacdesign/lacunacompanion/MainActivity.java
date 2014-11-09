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

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pgmacdesign.lacunacompanion.adapters.NavDrawerListAdapter;
import com.pgmacdesign.lacunacompanion.fragments.AboutUsCreditsFragment;
import com.pgmacdesign.lacunacompanion.fragments.BuildingFragment;
import com.pgmacdesign.lacunacompanion.fragments.DistanceCalculatorFragment;
import com.pgmacdesign.lacunacompanion.fragments.DonateFragment;
import com.pgmacdesign.lacunacompanion.fragments.HomeFragment;
import com.pgmacdesign.lacunacompanion.fragments.ListPlanetsFragment;
import com.pgmacdesign.lacunacompanion.fragments.LogOutInFragment;
import com.pgmacdesign.lacunacompanion.fragments.LookupPlanetFragment;
import com.pgmacdesign.lacunacompanion.fragments.MailFragment;
import com.pgmacdesign.lacunacompanion.fragments.OrbitXYCalculatorFragment;
import com.pgmacdesign.lacunacompanion.fragments.SettingsPreferencesFragment;
import com.pgmacdesign.lacunacompanion.fragments.ShipsFragment;
import com.pgmacdesign.lacunacompanion.fragments.SitterPWManagerFragment;
import com.pgmacdesign.lacunacompanion.fragments.UpgradeBuildQueueFragment;
import com.pgmacdesign.lacunacompanion.fragments.WebLinksFragment;
import com.pgmacdesign.lacunacompanion.slidingmenu.NavDrawerItem;

public class MainActivity extends Activity {
	
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
	8) Lookup Planet
	9) Orbit (x, y) Calculator
	10) Distance Calculator
	11) Sitter PW Manager
	12) Settings / Preferences
	13) About us / Credits (Don't forget Vas!)
	14) Donate
	15) Exit
	
	Extras to add at some point
	1) Notifications if a planet drops to negative happiness (IE notification bar)
	2) Mail in the correspondence tab (What people look for anyway)
	3) Keep a rolling counter of RPC usage somewhere
	4) Docked ship count and breakdown of ship types
	5) Ships in transit (incoming and outgoing)
	6) Recall all ships option
	7) Recall all spies option
	8) Upgrade progress via a status bar and the respective timer left
	9) On planet info: add plots, water, orbit, and breakdown of ore (composition)
	10) Empire boosts (Show and extend)
	11) View medals
	12) View species stats / affinities
	13) Rename planets
	14) Change planets / View planets / View SS via a spinner
	15) In mail, add select all function 
	16) Add link to in-game tutorial
	17) Add link to support (forums)
	18) Add a stats function (like the rankings in-game)
	19) Tips (like the ones at login, should be 48ish of them)
	20) Widgets for Planet build queue (IE, 001 Silmarilos will be done in 5d 4h 30m)
	 */
	
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
 
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
	
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
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
            R.drawable.ic_drawer, //nav menu toggle icon
            R.string.app_name, // nav drawer open - description for accessibility
            R.string.app_name // nav drawer close - description for accessibility 
            ){
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle(mTitle);
	                // calling onPrepareOptionsMenu() to show action bar icons
	                invalidateOptionsMenu();
	            }
	 
	            public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle(mDrawerTitle);
	                // calling onPrepareOptionsMenu() to hide action bar icons
	                invalidateOptionsMenu();
	            }
    		};
        		
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
		
	}
	
	//Initialize Variables
	private void Initialize(){
		
		//Shared Preferences Stuff
		settings = getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		
		//Everything else
		mTitle = mDrawerTitle = getTitle();
		 
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
 
        navDrawerItems = new ArrayList<NavDrawerItem>();

        //Add Nav drawer items to array
        
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1))); //First param is resource returned, second is what is returned if nothing is found
        // Log out / in
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Mail
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Upgrade / Build queue, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // List of planets
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // Web Links, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
        // Ships Info
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        // Building Info
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
        // Lookup Planet
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
        // Orbit (x, y) Calculator, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(9, -1), true, "22"));
        // Distance Calculator
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[10], navMenuIcons.getResourceId(10, -1)));
        // Sitter PW Manager, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[11], navMenuIcons.getResourceId(11, -1), true, "50+"));
        // Settings / Preferences
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[12], navMenuIcons.getResourceId(12, -1)));
        // About us / Credits
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[13], navMenuIcons.getResourceId(13, -1)));
        // Donate
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[14], navMenuIcons.getResourceId(14, -1)));   
        // Donate
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[15], navMenuIcons.getResourceId(15, -1)));   
         
        // Recycle the typed array
        navMenuIcons.recycle();
 
        //Set the list so that it is clickable and things will happen
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        try {
        	L.m("Line 226");
        } catch (Exception e){
        	L.m(e.toString());
        }

    	L.m("Line 231");
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
    	L.m("Line 234");
        getActionBar().setHomeButtonEnabled(true);
        L.m("Initialization Complete");
	}

	

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	//Called when invalidateOptionsMenu() is triggered
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}


	//Displays the respective fragment from the clicked on item in the drawer
	private void displayView(int position) {
		//Update the main content by replacing fragments
		Fragment fragment = null;

		switch (position) { 
		case 0:
			fragment = new HomeFragment();
			break;
			
		case 1:
			fragment = new LogOutInFragment();
			break;
			
		case 2:
			fragment = new MailFragment();
			break;
			
		case 3:
			fragment = new UpgradeBuildQueueFragment();
			break;
			
		case 4:
			fragment = new ListPlanetsFragment();
			break;
		case 5:
			fragment = new WebLinksFragment();
			break;
			
		case 6:
			fragment = new ShipsFragment();
			break;
			
		case 7:
			fragment = new BuildingFragment();
			break;
			
		case 8:
			fragment = new LookupPlanetFragment();
			break;
			
		case 9:
			fragment = new OrbitXYCalculatorFragment();
			break;
			
		case 10:
			fragment = new DistanceCalculatorFragment();
			break;
			
		case 11:
			fragment = new SitterPWManagerFragment();
			break;
			
		case 12:
			fragment = new SettingsPreferencesFragment();
			break;
			
		case 13:
			fragment = new AboutUsCreditsFragment();
			break;
			
		case 14:
			fragment = new DonateFragment();
			break;
			
		case 15:
			finish();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
			
		} else {
			//Fragment could not be created
			L.m("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	
	protected void onPause() {

		super.onPause();
		finish();
	}


}
