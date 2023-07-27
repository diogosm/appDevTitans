package br.edu.ufam.apptitans.usuariodao;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufam.apptitans.models.Item;
import br.edu.ufam.apptitans.models.Usuario;

public class ItemDAO {
    static ArrayList<Item> items = new ArrayList<>();
    private Context context;

    public ItemDAO(Context context){
        this.context = context;
    }

    public void criarItem(){
        Log.i("UsuarioDAO", "criandos usuarios...");
        items.add(new Item("sorridente", "Don't Say Goodbye"));
        items.add(new Item("triste", "Summertime Sadness"));
    }

    public ArrayList<Item> getList(){
        return items;
    }
}
