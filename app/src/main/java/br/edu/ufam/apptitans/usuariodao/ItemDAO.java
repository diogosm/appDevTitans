package br.edu.ufam.apptitans.usuariodao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

import br.edu.ufam.apptitans.models.Item;
import br.edu.ufam.apptitans.models.DB;
import br.edu.ufam.apptitans.models.Usuario;
import android.database.sqlite.*;

public class ItemDAO {
    static ArrayList<Item> items = new ArrayList<>();
    private Context context;
    private static final String DATABASE_NAME = "meu_banco.db";
    private static final String DATABASE_TABLE = "ITEM";

    public ItemDAO(Context context){
        this.context = context;
    }

    public void criarItem(){
        Log.i("UsuarioDAO", "criandos usuarios...");
//        items.add(new Item("sorridente", "Don't Say Goodbye"));
//        items.add(new Item("triste", "Summertime Sadness"));

        // passo 1. Criar o banco
        // table ITEM
        // colunas mood, musica
        String query_cria_banco = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "" +
                "(MOOD TEXT NOT NULL, MUSICA TEXT NOT NULL)";
        //chamo a variavel de banco
        DB db = new DB(context);
        SQLiteDatabase database = db.getDb();
        //executo a query
        database.execSQL(query_cria_banco);
        
        //cria 2 itens
        String query_insert = "INSERT INTO " + DATABASE_TABLE + " VALUES ('mood 1','musica 1')";
        database.execSQL(query_insert);
        query_insert = "INSERT INTO " + DATABASE_TABLE + " VALUES ('mood 2','musica 2')";
        database.execSQL(query_insert);
        Log.i("BANCO", "criei e add 2 moods");
    }

    public ArrayList<Item> getList(){
        //conecta no banco
        DB db = new DB(context);
        SQLiteDatabase database = db.getDb();

        String query_busca = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = database.rawQuery(query_busca, null);

        //defino uma variavel de retorno
        ArrayList<Item> itemsReposta = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") Item novoItem = new Item(
                        cursor.getString(cursor.getColumnIndex("MOOD")),
                        cursor.getString(cursor.getColumnIndex("MUSICA"))
                );
                itemsReposta.add(novoItem);
            } while(cursor.moveToNext());
        }

        Log.i("BANCO", "Consulta de busca feita!");

        return itemsReposta;
    }

    public boolean insereItem(Item item){
        //conexa com o banco
        DB db = new DB(context);
        SQLiteDatabase database = db.getDb();

        //crio a consulta
        String query_insert = "INSERT INTO " + DATABASE_TABLE + " VALUES (" +
                "'" + item.getMood().toString() + "', '" + item.getMusica().toString() +
                "')";
        database.execSQL(query_insert);
//        Cursor cursor = database.rawQuery(query_insert, null);
//        Log.i("BANCO", "Resultado do cursor " + cursor.getCount());
        return true;
    }
}
