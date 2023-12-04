package com.room.roomapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.room.roomapp.R;
import com.room.roomapp.database.ListaDatabase;
import com.room.roomapp.database.ProdutoDatabase;
import com.room.roomapp.model.Lista;
import com.room.roomapp.model.ListaProduto;
import com.room.roomapp.model.Produto;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InserirProduto extends AppCompatActivity {
    ExecutorService exs = Executors.newSingleThreadExecutor();
    Handler handler =  new Handler(Looper.getMainLooper());
    ProdutoDatabase produtoDatabase;

    EditText nomeProduto;
    TextView nomeLista;
    Button inserir, listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_produto);

        nomeLista = findViewById(R.id.nomeLista);
        nomeProduto = findViewById(R.id.nomeProduto);
        inserir = findViewById(R.id.inserir);
        listar = findViewById(R.id.listar);

        produtoDatabase = ProdutoDatabase.getDatabase(getApplicationContext());

        Produto produto = new Produto();

        Intent it = getIntent();

        long id_lista = it.getLongExtra("id_lista", 0);


        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produto.setNome(nomeProduto.getText().toString());
                produto.setId_lista(id_lista);
                inserirProduto(produto);
            }
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarListas();
            }
        });
    }

    public void listarListas() {
        exs.execute(new Runnable() {
            @Override
            public void run() {
                StringBuilder str = new StringBuilder();

                List<ListaProduto> lista = produtoDatabase.getDao().getAllListaProduto();

                for (ListaProduto lp : lista){
                    str.append(lp.lista.getNome());
                    str.append("\n");
                    for (Produto p: lp.produtos) {
                        str.append("- ");
                        str.append(p.getNome());
                        str.append("\n");
                    }
                    str.append("------------------");
                    str.append("\n");
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(InserirProduto.this, str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void inserirProduto(Produto produto) {
        exs.execute(new Runnable() {
            @Override
            public void run() {
                produtoDatabase.getDao().insert(produto);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(InserirProduto.this, produto.getNome() + " inserido com sucesso", Toast.LENGTH_SHORT).show();
                        nomeProduto.setText("");
                    }
                });
            }
        });
    }
}