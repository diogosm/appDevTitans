package br.edu.ufam.apptitans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.edu.ufam.apptitans.models.Usuario;
import br.edu.ufam.apptitans.usuariodao.UsuarioDAO;

public class MainActivity extends AppCompatActivity {

    private Button botaoLogin;
    private EditText login;
    private EditText senha;
    private int qtd;
    private static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.iniciarComponentes();
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.configuracoes){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setMessage("Clicado em configuracoes")
                            .setNeutralButton("Ok", null)
                            .show();
                    return true;
                }else if(id == R.id.about){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setMessage("Clicado em about")
                            .setNeutralButton("Ok", null)
                            .show();
                    return true;
                }
                return false;
            }

//            @Override
//            public boolean OnNavigationItemSelectedListener(){
//                return true;
//            }
        } );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meu_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.configuracoes){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Clicado em configuracoes")
                    .setNeutralButton("Ok", null)
                    .show();
            return true;
        }else if(id == R.id.about){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Clicado em about")
                    .setNeutralButton("Ok", null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void iniciarComponentes() {
        this.botaoLogin = findViewById(R.id.botaoLogin);
        this.login = findViewById(R.id.login);
        this.senha = findViewById(R.id.senha);
        this.qtd = 0;
        UsuarioDAO.criarUsuarios(); //cria usuarios qndo starta o app

        this.botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginStr, senhaStr;
                loginStr = login.getText().toString();
                senhaStr = senha.getText().toString();

                Usuario usuario = new Usuario(loginStr, senhaStr);

//                if(true){ //testa usuario e senha
                if( !UsuarioDAO.temPermissao(usuario) ){
                    TextView erro = findViewById(R.id.erro);

                    ViewGroup.LayoutParams params = erro.getLayoutParams();
                    params.height = 48;
                    erro.setLayoutParams(params);

                    erro.setEms(10);
                    erro.setVisibility(View.VISIBLE);
                    erro.setText("Usuario ou senha inválidos");

                    Log.i(TAG, "botao clicado "
                            + (qtd++)
                            + ". Login: "
                            + loginStr
                            + " Senha: "
                            + senhaStr);
                }else{
                    //chamo uma nova tela
                    Log.i(TAG, "Usuario com logado com sucesso!");
                    TextView erro = findViewById(R.id.erro);

                    ViewGroup.LayoutParams params = erro.getLayoutParams();
                    params.height = 0;
                    erro.setLayoutParams(params);
                    erro.setVisibility(View.INVISIBLE);

                    Intent minhaIntent = new Intent(MainActivity.this, home_activity.class);
                    minhaIntent.putExtra("usuario", usuario.getLogin());
                    startActivity(minhaIntent);
                }
            }
        });
    }
}