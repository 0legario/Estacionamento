package com.example.estacionamento;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.estacionamento.DAO.VagaDAO;
import com.example.estacionamento.models.Vaga;

import java.util.ArrayList;
import java.util.List;

public class VagaActivity extends AppCompatActivity {
    Button cad, upd, del;
    VagaDAO vagaDAO;
    EditText numeroVaga, mensalidade;
    private ArrayAdapter<String> adapter;
    private List<String> listaVagas;
    private ListView listView;
    List<Vaga> vagas;
    String id_vaga;
    TextView aviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vaga);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        aviso = findViewById(R.id.textView7);

        cad = findViewById(R.id.bt_cad);
        upd = findViewById(R.id.bt_upd);
        del = findViewById(R.id.bt_del);

        numeroVaga = findViewById(R.id.editTextText);
        mensalidade = findViewById(R.id.editTextText2);

        listView = findViewById(R.id.listViewListar);

        vagaDAO = new VagaDAO(this);
        carregarLista();


        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vaga vaga = new Vaga(Integer.parseInt(numeroVaga.getText().toString()), Integer.parseInt(mensalidade.getText().toString()));
                vagaDAO.salvar(vaga);
                carregarLista();
            }
        });

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vaga vaga = new Vaga(Integer.parseInt(numeroVaga.getText().toString()), Integer.parseInt(mensalidade.getText().toString()));
                vaga.setId_vaga(Integer.parseInt(id_vaga));
                vagaDAO.alterar(vaga);
                carregarLista();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vaga vaga = new Vaga(Integer.parseInt(numeroVaga.getText().toString()), Integer.parseInt(mensalidade.getText().toString()));
                vaga.setId_vaga(Integer.parseInt(id_vaga));
                vagaDAO.deletar(vaga);
                carregarLista();
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) listView.getItemAtPosition(position);
                String palavra[] = conteudo.split(" - ");
                id_vaga = palavra[0];
                aviso.setText("ID vaga selecionado: " + id_vaga);
                vagas = vagaDAO.listar();
                Vaga vaga = vagas.get(position);
                numeroVaga.setText(vaga.getNumero_vaga() + "");
                mensalidade.setText(vaga.getMensalidade() + "");
            }
        });

    }

    private void carregarLista(){
     vagas = vagaDAO.listar();
        listaVagas = new ArrayList<>();

        for (Vaga vaga : vagas){
            listaVagas.add(vaga.getId_vaga()+" - "+"Número da vaga:" + vaga.getNumero_vaga() + " - Mensalidade: " + vaga.getMensalidade());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaVagas);
        listView.setAdapter(adapter);
    }
}