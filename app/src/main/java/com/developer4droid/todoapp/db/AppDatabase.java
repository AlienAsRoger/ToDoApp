package com.developer4droid.todoapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.developer4droid.todoapp.db.entities.Note;
import com.developer4droid.todoapp.db.interfaces.NoteDao;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 15.08.2017
 * Time: 6:51
 */

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	public abstract NoteDao userDao();

	private static AppDatabase instance;

	public static AppDatabase getAppDatabase(Context context) {
		if (instance == null) {
			instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "notes-database")
							.build();
		}
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}
}