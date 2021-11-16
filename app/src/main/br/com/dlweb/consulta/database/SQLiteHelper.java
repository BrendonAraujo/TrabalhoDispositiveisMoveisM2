package br.com.dlweb.consulta.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private String sqlCreate;
    private String sqlDelete;

    public SQLiteHelper(Context context, String name, int version, String sqlCreate, String sqlDelete) {
        super(context, name, null, version);
        this.sqlCreate = sqlCreate;
        this.sqlDelete = sqlDelete;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDelete);
        onCreate(db);
    }
}
