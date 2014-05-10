package com.pirasisl.android.myshopping.ui;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.pirasisl.android.myshopping.R;
import com.pirasisl.android.myshopping.R.layout;
import com.pirasisl.android.myshopping.R.string;
import com.pirasisl.android.myshopping.persistence.ShoppingListDBHelper;

import java.util.List;

public class ShoppingListActivity extends BaseActivity {

	private ShoppingListDBHelper shoppingListDBHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(string.title_section_shopping_list);

		shoppingListDBHelper = new ShoppingListDBHelper(this);

		ListView listView = (ListView) findViewById(R.id.listView);

		List<ShoppingListDBHelper.ShoppingItem> shoppingItems = shoppingListDBHelper.getShoppingItems();

		final ShoppingListAdapter adapter = new ShoppingListAdapter(this, android.R.layout.simple_list_item_1, shoppingItems);
		listView.setAdapter(adapter);
	}

	protected int getContentViewId() {
		return layout.activity_shoppinglist;
	}

	private class ShoppingListAdapter extends ArrayAdapter<ShoppingListDBHelper.ShoppingItem> {
		private final Context context;
		private final List<ShoppingListDBHelper.ShoppingItem> items;

		public ShoppingListAdapter(Context context, int resId, List<ShoppingListDBHelper.ShoppingItem> items) {
			super(context, resId, items);
			this.context = context;
			this.items = items;
		}

		public long getItemId(int position) {
			if (position < items.size()) {
				return items.get(position).id;
			} else {
				return -1;
			}
		}
	}
}
