package com.developer4droid.todoapp.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 10.08.2017
 * Time: 21:40
 */

@Entity(tableName = "notes")
public class Note {
	@PrimaryKey
	private int uid;

	@ColumnInfo(name = "note_title")
	private String note;

	public Note(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
}
