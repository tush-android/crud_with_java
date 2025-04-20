package com.example.crud_with_java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="notes.db";
    private static final int DATABASE_version=1;
    private static final String TABLE_NAME="user_info";
    private static final String user_id="id";
    private static final String Name="name";
    private static final String Email="email";
    private static final String Mobile="mobile";

    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_NAME + " (" +
                user_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Name + " TEXT, " +
                Email + " TEXT, " +
                Mobile + " TEXT)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String d="DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(d);
        onCreate(db);
        //DROP TABLE IF EXISTS
    }
    public void insert(Note note){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Name,note.name);
        values.put(Email,note.email);
        values.put(Mobile,note.mobile);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public List<Note> getAllUsers(){
        List<Note> userList=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor c=db.rawQuery(query,null);
        while(c.moveToNext()){
            int id=c.getInt(c.getColumnIndexOrThrow(user_id));
            String name=c.getString(c.getColumnIndexOrThrow(Name));
            String email=c.getString(c.getColumnIndexOrThrow(Email));
            String mob=c.getString(c.getColumnIndexOrThrow(Mobile));
            Note note=new Note(id,name,email,mob);
            userList.add(note);
        }
        c.close();
        db.close();
        return userList;
    }
    public void updateuser(Note note){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Name,note.name);
        values.put(Email,note.email);
        values.put(Mobile,note.mobile);
        String where=user_id+"=? ";
        String whereargs[]={String.valueOf(note.id())};
        db.update(TABLE_NAME,values,where,whereargs);
        db.close();
    }
    public void deletenote(int noteId){
        SQLiteDatabase db=getWritableDatabase();
        String where=user_id+"=? ";
        String[] whereargs={String.valueOf(noteId)};
        db.delete(TABLE_NAME,where,whereargs);
        db.close();

    }
}
