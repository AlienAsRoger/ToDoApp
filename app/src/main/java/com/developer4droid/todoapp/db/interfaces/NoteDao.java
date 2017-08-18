package com.developer4droid.todoapp.db.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.developer4droid.todoapp.db.entities.Note;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 15.08.2017
 * Time: 6:05
 */

@Dao
public interface NoteDao {

	@Query("SELECT * FROM notes")
	List<Note> getAll();

	@Query("SELECT * FROM notes WHERE uid IN (:notesIds)")
	List<Note> loadAllByIds(int[] notesIds);

	@Query("SELECT * FROM notes WHERE note_title LIKE :title LIMIT 1")
	Note findByTitle(String title);

	@Insert
	void insertAll(Note... note);

	@Delete
	void delete(Note note);
}
