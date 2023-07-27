package br.edu.ufam.apptitans.usuariodao;

import android.util.Log;

import java.util.HashMap;

import br.edu.ufam.apptitans.models.Usuario;

public class UsuarioDAO {
    static HashMap<String, String> logins = new HashMap<>();

    public static void criarUsuarios(){
        Log.i("UsuarioDAO", "criandos usuarios...");
        logins.put("admin", "123123");
    }

    public static boolean temPermissao(Usuario u){
        if(!logins.containsKey(u.getLogin()))
            return false;
        else{
            String senhaPraVerificar = logins.get(u.getLogin());
            return senhaPraVerificar.equals(u.getSenha());
        }
    }
}
