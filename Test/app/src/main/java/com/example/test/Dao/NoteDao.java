package com.example.test.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.Db.noteDBHelper;
import com.example.test.bean.HistoryBean;

import java.util.ArrayList;
import java.util.List;


public class NoteDao {
    Context context;
    noteDBHelper dbHelper;

    public NoteDao(Context context) {
        this.context = context;
        dbHelper = new noteDBHelper(context, "note.db", null, 1);
    }

    public void insertNote(HistoryBean bean) {

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("createTime", bean.getCreateTime());
        cv.put("score", bean.getScore());

        sqLiteDatabase.insert("note_data", null, cv);
    }

    public void updateNote(HistoryBean bean) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("createTime", bean.getCreateTime());
        cv.put("score", bean.getScore());

        db.update("note_data", cv, "note_id=?", new String[]{bean.getId() + ""});
        db.close();
    }

    public void delete(HistoryBean bean) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete("note_data", "note_id=?", new String[]{bean.getId() + ""});
    }


    public List<HistoryBean> queryNotesAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<HistoryBean> noteList = new ArrayList<>();
        HistoryBean note;
        String sql = "select * from note_data wher order by score desc";
        Cursor cursor = null;

        cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            note = new HistoryBean();
            note.setId(cursor.getInt(cursor.getColumnIndex("note_id")));
            note.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
            note.setScore(cursor.getString(cursor.getColumnIndex("score")));
            noteList.add(note);
        }

        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }

        return noteList;
    }


}
