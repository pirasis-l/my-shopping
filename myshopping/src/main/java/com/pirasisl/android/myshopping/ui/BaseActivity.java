package com.pirasisl.android.myshopping.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;

import com.pirasisl.android.myshopping.R.id;
import com.pirasisl.android.myshopping.R.layout;
import com.pirasisl.android.myshopping.R.menu;

public class BaseActivity extends Activity {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentViewId());

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(id.navigation_drawer);

		mNavigationDrawerFragment.setUp(id.navigation_drawer, (DrawerLayout) findViewById(id.drawer_layout));
	}

	public boolean onCreateOptionsMenu(Menu options) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(menu.home, options);
			return true;
		}
		return super.onCreateOptionsMenu(options);
	}

	protected int getContentViewId() {
		return layout.activity_base;
	}
}
