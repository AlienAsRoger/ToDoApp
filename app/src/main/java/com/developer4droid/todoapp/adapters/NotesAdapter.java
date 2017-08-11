package com.developer4droid.todoapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.developer4droid.todoapp.R;
import com.developer4droid.todoapp.entities.Note;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 10.08.2017
 * Time: 21:39
 */

public class NotesAdapter extends ArrayAdapter<Note> {


	public NotesAdapter(@NonNull Context context, @NonNull List<Note> objects) {
		super(context, 0, objects);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		if (convertView == null) {
			convertView = createView();
		}
		bindView(position, convertView);
		return convertView ;
	}

	private View createView() {
		ViewHolder holder = new ViewHolder();
		View view = LayoutInflater.from(getContext()).inflate(R.layout.note_item, null, false);

		holder.text = view.findViewById(R.id.textTxt);

		view.setTag(holder);
		return view;
	}

	private void bindView(int position, View convertView) {
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.text.setText(getItem(position).getNote());
	}

	public static class ViewHolder {

		public TextView text;
	}
}

