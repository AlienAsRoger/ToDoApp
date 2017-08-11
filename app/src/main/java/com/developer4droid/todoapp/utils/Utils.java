package com.developer4droid.todoapp.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.developer4droid.todoapp.entities.Note;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 04.08.2017
 * Time: 6:04
 */

public class Utils {

	public static final String FILENAME = "todo.txt";


	public static void showKeyboard(View view, Context context) {
		if (view.requestFocus()) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
		}
	}

	public static void hideKeyboard(View view, Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static List<Note> readItems(Context context) {
		File filesDir = context.getFilesDir();
		File todoFile = new File(filesDir, FILENAME);
		List<String> items;
		List<Note> notes = new ArrayList<>();

		try {
			items = new ArrayList<>(FileUtils.readLines(todoFile));

			for (String item : items) {
				notes.add(new Note(item));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return notes;
	}

	public static void writeItems(Context context, List<Note> notes) {
		File filesDir = context.getFilesDir();
		File todoFile = new File(filesDir, FILENAME);

		try {
			List<String> items = new ArrayList<>();
			for (Note item : notes) {
				items.add(item.getNote());
			}

			FileUtils.writeLines(todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
