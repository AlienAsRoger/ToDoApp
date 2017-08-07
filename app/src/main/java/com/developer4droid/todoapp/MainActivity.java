package com.developer4droid.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	public static final String FILENAME = "todo.txt";
	public static final String POSITION = "position";
	public static final String STRING = "string";

	private ListView lvItems;
	private List<String> items;
	private ArrayAdapter<String> itemsAdapter;

	private final int EDIT_FIELD = 11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		readItems();
		itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

		lvItems = findViewById(R.id.lvItems);
		lvItems.setAdapter(itemsAdapter);

		setupListViewListener();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == EDIT_FIELD && resultCode == Activity.RESULT_OK) {
			int position = data.getIntExtra(POSITION, 0);
			String string = data.getStringExtra(STRING);
			items.remove(position);
			items.add(position, string);
			itemsAdapter.notifyDataSetChanged();

			writeItems();
		}
	}

	private void setupListViewListener() {
		lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
				items.remove(pos);
				itemsAdapter.notifyDataSetChanged();
				writeItems();
				return false;
			}
		});
		lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
				Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
				intent.putExtra(POSITION, pos);
				intent.putExtra(STRING, items.get(pos));
				startActivityForResult(intent, EDIT_FIELD);
			}
		});
	}

	public void onAddItem(View view) {
		EditText etNewItem = findViewById(R.id.etNewItem);
		if (etNewItem.getText() != null) {
			itemsAdapter.add(etNewItem.getText().toString());
			etNewItem.setText("");
		}
		writeItems();

		lvItems.smoothScrollToPosition(itemsAdapter.getCount() - 1);
	}

	private void readItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, FILENAME);

		try {
			items = new ArrayList<>(FileUtils.readLines(todoFile));
		} catch (IOException e) {
			e.printStackTrace();
			items = new ArrayList<>();
		}
	}

	private void writeItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, FILENAME);

		try {
			FileUtils.writeLines(todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
