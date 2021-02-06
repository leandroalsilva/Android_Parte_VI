package com.example.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.R;
import com.example.ceep.dao.NotaDAO;
import com.example.ceep.model.Nota;
import com.example.ceep.ui.recyclerview.adapter.ListaNotasAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        List<Nota> todasNotas = notasDeExemplo();

        TextView botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
        botaoInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciaFormularioNota = new Intent(ListaNotasActivity.this,
                        FormularioNotaActivity.class);
                startActivity(iniciaFormularioNota);
            }
        });
    }

    @Override
    protected void onResume() {
        NotaDAO dao = new NotaDAO();
        List<Nota> todasNotas = dao.todos();
        configuraRecyclerView(todasNotas);
        super.onResume();
    }

    private List<Nota> notasDeExemplo() {
        NotaDAO dao = new NotaDAO();
            dao.insere(new Nota("Primeira nota", "Descrição pequena "));
            dao.insere(new Nota("Segunda nota", "Descrição grande bem maior que a " +
                    "primeira para testar formato da nota "));
        return dao.todos();
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);
    }


    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        listaNotas.setAdapter(new ListaNotasAdapter(this, todasNotas));
    }
}