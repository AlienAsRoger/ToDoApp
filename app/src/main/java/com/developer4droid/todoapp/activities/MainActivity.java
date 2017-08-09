package com.developer4droid.todoapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.developer4droid.todoapp.R;
import com.developer4droid.todoapp.fragments.EditTextDialogFragment;
import com.developer4droid.todoapp.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EditTextDialogFragment.EditTextInterface {

	public static final String POSITION = "position";
	public static final String STRING = "string";
	public static final String FRAGMENT_EDIT_NAME = "fragment_edit_name";

	private ListView lvItems;
	private View mainLayout;
	private View fillView;
	private List<String> items;
	private ArrayAdapter<String> itemsAdapter;

	private final static int EDIT_FIELD = 11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		items = Utils.readItems(this);
		itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

		mainLayout = findViewById(R.id.mainLayout);
		fillView = findViewById(R.id.fillView);
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
			updateItemAtPosition(position, string);
		}
	}

	private void setupListViewListener() {
		lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
				items.remove(pos);
				itemsAdapter.notifyDataSetChanged();
				Utils.writeItems(MainActivity.this, items);
				return true;
			}
		});
		lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//				Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
//				intent.putExtra(POSITION, pos);
//				intent.putExtra(STRING, items.get(pos));
//				startActivityForResult(intent, EDIT_FIELD);

				showEditDialog(pos);
			}
		});
	}

	public void onAddItem(View view) {
		EditText etNewItem = findViewById(R.id.etNewItem);
		if (etNewItem.getText() != null) {
			String text = etNewItem.getText().toString();

			// Don't add empty items
			if (TextUtils.isEmpty(text)) {
				return;
			}
			itemsAdapter.add(text);
			etNewItem.setText("");
		} else {
			return;
		}
		Utils.writeItems(this, items);

		// scroll to the end of list
		lvItems.smoothScrollToPosition(itemsAdapter.getCount() - 1);
	}

	private void showEditDialog(int pos) {
		FragmentManager manager = getSupportFragmentManager();
		EditTextDialogFragment fragment = EditTextDialogFragment.newInstance(items.get(pos), pos);
		fragment.show(manager, FRAGMENT_EDIT_NAME);
	}

	private void updateItemAtPosition(int position, String string) {
		items.remove(position);
		items.add(position, string);
		itemsAdapter.notifyDataSetChanged();

		Utils.writeItems(this, items);
	}

	@Override
	public void onTextEdited(String string, int position) {
		updateItemAtPosition(position, string);

	}
}