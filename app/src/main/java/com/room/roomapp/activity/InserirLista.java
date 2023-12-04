package com.room.roomapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.room.roomapp.R;
import com.room.roomapp.database.ListaDatabase;
import com.room.roomapp.model.Lista;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InserirLista extends AppCompatActivity {

    ExecutorService exs = Executors.newSingleThreadExecutor();
    Handler handler =  new Handler(Looper.getMainLooper());
    ListaDatabase listaDatabase;

    Button inserir;
    EditText nomeLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inserir = findViewById(R.id.inserir);
        nomeLista = findViewById(R.id.nomeLista);

        listaDatabase = ListaDatabase.getDatabase(getApplicationContext());

        Lista lista = new Lista();

        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista.setNome(nomeLista.getText().toString());
                inserirLista(lista);
            }
        });
    }

    public void inserirLista(Lista lista) {
        exs.execute(new Runnable() {
            @Override
            public void run() {
                long id = listaDatabase.getDao().insert(lista);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(InserirLista.this, lista.getNome() + " inserida com sucesso", Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(InserirLista.this, InserirProduto.class);
                        it.putExtra("id_lista", id);

                        startActivity(it);
                    }
                });
            }
        });
    }
}