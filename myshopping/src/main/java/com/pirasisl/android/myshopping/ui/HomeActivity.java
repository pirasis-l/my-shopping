package com.pirasisl.android.myshopping.ui;

import android.os.Bundle;
import android.view.Menu;

import com.pirasisl.android.myshopping.R.id;
import com.pirasisl.android.myshopping.R.layout;
import com.pirasisl.android.myshopping.R.menu;
import com.pirasisl.android.myshopping.R.string;

public class HomeActivity extends BaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(string.title_section_home);
	}

	public boolean onCreateOptionsMenu(Menu options) {
		return super.onCreateOptionsMenu(options);
	}
}
