package br.edu.ufam.apptitans.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB  {
    private SQLiteDatabase database;

    public DB(Context context){
        database = context.openOrCreateDatabase("meu_banco.db", Context.MODE_PRIVATE,
                null);
    }

    public SQLiteDatabase getDb(){
        return this.database;
    }
}
