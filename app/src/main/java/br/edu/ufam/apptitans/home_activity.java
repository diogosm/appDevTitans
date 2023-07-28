package br.edu.ufam.apptitans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class home_activity extends AppCompatActivity implements MyModalFragment.ModalFragmentListener {

    private TextView bemVindo;
    private static final String TAG_MODAL_FRAGMENT = "modal_fragment";
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String usuarioLogin = intent.getStringExtra("usuario");

        //seta o botao de add
        FloatingActionButton floatingActionButton = findViewById(R.id.FloatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chama um modal para salvar
                MyModalFragment modalFragment = new MyModalFragment();
                modalFragment.setModalFragmentListener(home_activity.this);
                modalFragment.show(getSupportFragmentManager(), TAG_MODAL_FRAGMENT);
            }
        });

        //seta o comportamento do menu de baixo
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.configuracoes){
                    AlertDialog.Builder alert = new AlertDialog.Builder(home_activity.this);
                    alert.setMessage("Clicado em configuracoes")
                            .setNeutralButton("Ok", null)
                            .show();
                    return true;
                }else if(id == R.id.about){
                    AlertDialog.Builder alert = new AlertDialog.Builder(home_activity.this);
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

        //inicializa a lista de dados
        recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ItemsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onButtonClicked(String v1, String v2){
        adapter.update();
        adapter.notifyDataSetChanged();
        Log.i("BANCO", "Atualizando o recycler");
    }
}