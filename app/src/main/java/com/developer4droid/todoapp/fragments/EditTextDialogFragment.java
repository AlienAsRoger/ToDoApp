package com.developer4droid.todoapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.developer4droid.todoapp.R;

import static com.developer4droid.todoapp.activities.MainActivity.POSITION;
import static com.developer4droid.todoapp.activities.MainActivity.STRING;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.08.2017
 * Time: 21:25
 */

public class EditTextDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

	private EditText editText;
	private String string;
	private int position;


	public interface EditTextInterface {
		void onTextEdited(String string, int position);
	}

	public EditTextDialogFragment() {
	}

	public static EditTextDialogFragment newInstance(String text, int pos) {
		EditTextDialogFragment frag = new EditTextDialogFragment();
		Bundle args = new Bundle();
		args.putString(STRING, text);
		args.putInt(POSITION, pos);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			string = savedInstanceState.getString(STRING);
			position = savedInstanceState.getInt(POSITION);
		} else {
			string = getArguments().getString(STRING);
			position = getArguments().getInt(POSITION);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.edit_item_layout, container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		editText = view.findViewById(R.id.editText);

		// update UI
		editText.setText(string);
		editText.setSelection(editText.getText().length());
		editText.setOnEditorActionListener(this);


		// Show soft keyboard automatically and request focus to field
		editText.requestFocus();
		getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
	}

	@Override
	public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			if (editText.getText() == null) {
				return false;
			}
			// Return input text back to activity through the implemented listener
			EditTextInterface listener = (EditTextInterface) getActivity();
			listener.onTextEdited(editText.getText().toString(), position);
			// Close the dialog and return back to the parent activity
			dismiss();
			return true;
		}
		return false;
	}

}
