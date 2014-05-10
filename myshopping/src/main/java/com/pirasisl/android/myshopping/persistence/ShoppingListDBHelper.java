package com.pirasisl.android.myshopping.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListDBHelper {

	private static final String DB_NAME = "my_db";
	private static final int DB_VERSION = 1;

	private static final String TABLE_NAME_SHOPPING_ITEMS = "shoppingitems";
	private static final String KEY_SHOPPING_ITEMS_ROWID = "id";
	private static final String KEY_SHOPPING_ITEMS_NAME = "name";
	private static final String KEY_SHOPPING_ITEMS_WEIGHT = "weight";

	private final Context context;

	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;

	public ShoppingListDBHelper(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	private void open() throws SQLException {
		open(false);
	}

	private void open(boolean writable) throws SQLException {
		if (writable) {
			db = dbHelper.getWritableDatabase();
		} else {
			db = dbHelper.getReadableDatabase();
		}
	}

	private void close() throws SQLException {
		dbHelper.close();
	}

	public List<ShoppingItem> getShoppingItems() {
		List<ShoppingItem> list = new ArrayList<ShoppingItem>();
		try {

			this.open();

			Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_SHOPPING_ITEMS + " ORDER BY " + KEY_SHOPPING_ITEMS_WEIGHT, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				int id = cursor.getInt(cursor.getColumnIndex(KEY_SHOPPING_ITEMS_ROWID));
				String name = cursor.getString(cursor.getColumnIndex(KEY_SHOPPING_ITEMS_NAME));
				int weight = cursor.getInt(cursor.getColumnIndex(KEY_SHOPPING_ITEMS_WEIGHT));
				list.add(new ShoppingItem(id, name, weight));
			}
			cursor.close();

			this.close();

			list.add(new ShoppingItem(10, "tooth paste", 0));
			list.add(new ShoppingItem(11, "washing powder", 0));

			return list;
		} catch (Exception e) {
			Log.e("Shopping list DB error", e.getMessage());
		}

		return list;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL("create table " + TABLE_NAME_SHOPPING_ITEMS + " (" + KEY_SHOPPING_ITEMS_ROWID + " integer primary key autoincrement, "
						+ KEY_SHOPPING_ITEMS_NAME + " text, " + KEY_SHOPPING_ITEMS_WEIGHT + " integer);");
			} catch (Exception e) {

			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}

	public class ShoppingItem {
		public int id;
		public String name;
		public int weight;

		public ShoppingItem(int id, String name, int weight) {
			this.id = id;
			this.name = name;
			this.weight = weight;
		}

		public String toString() {
			return name;
		}
	}
}
