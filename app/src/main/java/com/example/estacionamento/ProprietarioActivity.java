package com.example.estacionamento;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.estacionamento.DAO.ProprietarioDAO;
import com.example.estacionamento.DAO.VagaDAO;
import com.example.estacionamento.models.Proprietario;
import com.example.estacionamento.models.Vaga;

import java.util.ArrayList;
import java.util.List;

public class ProprietarioActivity extends AppCompatActivity {
    Button cad, upd, del;
    VagaDAO vagaDAO;
    ProprietarioDAO proprietarioDAO;
    EditText nome, cpf, email, senha;
    Spinner spinner;
    private ArrayAdapter<String> adapter;
    private List<String> listaVagas;
    private List<String> listaProprietarios;
    private ListView listView;
    List<Vaga> vagas;
    List<Proprietario> proprietarios;
    String id_prop;
    TextView aviso;
    int numeroVaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proprietario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        aviso = findViewById(R.id.textView8);

        cad = findViewById(R.id.bt_cad);
        upd = findViewById(R.id.bt_upd);
        del = findViewById(R.id.bt_del);

        nome = findViewById(R.id.editTextText);
        cpf = findViewById(R.id.editTextText2);
        email = findViewById(R.id.editTextText3);
        senha = findViewById(R.id.editTextText4);

        spinner = findViewById(R.id.spinner);

        listView = findViewById(R.id.listViewListar);

        vagaDAO = new VagaDAO(this);
        proprietarioDAO = new ProprietarioDAO(this);
        carregarLista();


        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conteudo = (String) spinner.getSelectedItem();
                String palavra[] = conteudo.split(" - ");
                int fkVaga = Integer.parseInt(palavra[0]);
                Proprietario proprietario = new Proprietario(fkVaga, nome.getText().toString(), cpf.getText().toString(), email.getText().toString(), senha.getText().toString());
                proprietarioDAO.salvar(proprietario);
                carregarLista();
            }
        });

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conteudo = (String) spinner.getSelectedItem();
                String palavra[] = conteudo.split(" - ");
                int fkVaga = Integer.parseInt(palavra[0]);
                Proprietario proprietario = new Proprietario(fkVaga, nome.getText().toString(), cpf.getText().toString(), email.getText().toString(), senha.getText().toString());
                proprietario.setId_proprietario(Integer.parseInt(id_prop));
                proprietarioDAO.alterar(proprietario);
                carregarLista();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conteudo = (String) spinner.getSelectedItem();
                String palavra[] = conteudo.split(" - ");
                int fkVaga = Integer.parseInt(palavra[0]);
                Proprietario proprietario = new Proprietario(fkVaga, nome.getText().toString(), cpf.getText().toString(), email.getText().toString(), senha.getText().toString());
                proprietario.setId_proprietario(Integer.parseInt(id_prop));
                proprietarioDAO.deletar(proprietario);
                carregarLista();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) listView.getItemAtPosition(position);
                String palavra[] = conteudo.split(" - ");
                id_prop = palavra[0];
                aviso.setText("ID proprietário selecionado: " + id_prop);

                proprietarios = proprietarioDAO.listar();
                Proprietario proprietario1 = proprietarios.get(position);
                nome.setText(proprietario1.getNome());
                cpf.setText(proprietario1.getCpf());
                email.setText(proprietario1.getEmail());
                senha.setText(proprietario1.getSenha());

                vagas = vagaDAO.listar();

                    for (Vaga vaga : vagas) {
                        if (vaga.getId_vaga() == proprietario1.getFk_vaga()) {
                            spinner.setSelection(vagas.indexOf(vaga));
                        }

                }


            }
        });

    }

    private void carregarLista() {
        vagas = vagaDAO.listar();
        listaVagas = new ArrayList<>();
        for (Vaga vaga : vagas) {
            listaVagas.add(vaga.getId_vaga() + " - " + "Número da vaga:" + vaga.getNumero_vaga());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaVagas);
        spinner.setAdapter(adapter);

        proprietarios = proprietarioDAO.listar();
        listaProprietarios = new ArrayList<>();


        for (Proprietario proprietario : proprietarios) {
            for (Vaga vaga : vagas) {
                if (vaga.getId_vaga() == proprietario.getFk_vaga()) {
                    numeroVaga = vaga.getNumero_vaga();
                }
            }
            listaProprietarios.add(proprietario.getId_proprietario() + " - " + "Nome: " + proprietario.getNome() + "\nCPF: " + proprietario.getCpf() + "\nEmail: " + proprietario.getEmail() +
                    "\nSenha: " + proprietario.getSenha() + "\nNumero Vaga: " + numeroVaga);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProprietarios);
        listView.setAdapter(adapter);

    }




    }

