package com.developer4droid.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

	public static final String FILENAME = "todo.txt";
	private int position;
	private EditText editText;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_edit_item);

		editText = findViewById(R.id.editText);

		// get intent with data
		Intent intent = getIntent();
		String string = intent.getStringExtra(MainActivity.STRING);
		position = intent.getIntExtra(MainActivity.POSITION, 0);

		// update UI
		editText.setText(string);
	}

	public void onSaveEdit(View view) {
		if (editText.getText() != null) {
			String string = editText.getText().toString();
			editText.setText("");

			Intent data = new Intent();
			data.putExtra(MainActivity.POSITION, position);
			data.putExtra(MainActivity.STRING, string);

			setResult(Activity.RESULT_OK, data);
			finish();
		}
	}

}
