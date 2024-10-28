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
import com.example.estacionamento.DAO.VeiculoDAO;
import com.example.estacionamento.models.Proprietario;
import com.example.estacionamento.models.Vaga;
import com.example.estacionamento.models.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoActivity extends AppCompatActivity {

    Button cad, upd, del;
    ProprietarioDAO proprietarioDAO;
    VeiculoDAO veiculoDAO;
    EditText placa, ano, mensalidade;
    Spinner spinner;
    private ArrayAdapter<String> adapter;
    private List<String> listaProprietarios;
    private List<String> listaVeiculos;
    private ListView listView;
    List<Proprietario> proprietarios;
    List<Veiculo> veiculos;
    String id_veiculo;
    TextView aviso;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_veiculo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        aviso = findViewById(R.id.textView8);

        cad = findViewById(R.id.bt_cad);
        upd = findViewById(R.id.bt_upd);
        del = findViewById(R.id.bt_del);

        placa = findViewById(R.id.editTextText);
        mensalidade = findViewById(R.id.editTextText2);
        ano = findViewById(R.id.editTextText3);

        spinner = findViewById(R.id.spinner);

        listView = findViewById(R.id.listViewListar);

        veiculoDAO = new VeiculoDAO(this);
        proprietarioDAO = new ProprietarioDAO(this);
        carregarLista();


        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conteudo = (String) spinner.getSelectedItem();
                String palavra[] = conteudo.split(" - ");
                int fkProp = Integer.parseInt(palavra[0]);
                Veiculo veiculo = new Veiculo(Integer.parseInt(mensalidade.getText().toString()), Integer.parseInt(ano.getText().toString()), fkProp, placa.getText().toString());
                veiculoDAO.salvar(veiculo);
                carregarLista();
            }
        });

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conteudo = (String) spinner.getSelectedItem();
                String palavra[] = conteudo.split(" - ");
                int fkProp = Integer.parseInt(palavra[0]);
                Veiculo veiculo = new Veiculo(Integer.parseInt(mensalidade.getText().toString()), Integer.parseInt(ano.getText().toString()), fkProp, placa.getText().toString());
                veiculo.setId_veiculo(Integer.parseInt(id_veiculo));
                veiculoDAO.alterar(veiculo);
                carregarLista();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conteudo = (String) spinner.getSelectedItem();
                String palavra[] = conteudo.split(" - ");
                int fkProp = Integer.parseInt(palavra[0]);
                Veiculo veiculo = new Veiculo(Integer.parseInt(mensalidade.getText().toString()), Integer.parseInt(ano.getText().toString()), fkProp, placa.getText().toString());
                veiculo.setId_veiculo(Integer.parseInt(id_veiculo));
                veiculoDAO.deletar(veiculo);
                carregarLista();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) listView.getItemAtPosition(position);
                String palavra[] = conteudo.split(" - ");
                id_veiculo = palavra[0];
                aviso.setText("ID veículo selecionado: " + id_veiculo);

                veiculos = veiculoDAO.listar();
                Veiculo veiculo1 = veiculos.get(position);
                placa.setText(veiculo1.getPlaca());
                ano.setText(veiculo1.getAno() + "");
                mensalidade.setText(veiculo1.getMensalidade() + "");

                proprietarios = proprietarioDAO.listar();

                for (Proprietario proprietario: proprietarios) {
                    if (proprietario.getId_proprietario() == veiculo1.getFk_proprietario()) {
                        spinner.setSelection(proprietarios.indexOf(proprietario));
                    }

                }


            }
        });

    }

    private void carregarLista() {
        proprietarios = proprietarioDAO.listar();
        listaProprietarios = new ArrayList<>();
        for (Proprietario proprietario : proprietarios) {
            listaProprietarios.add(proprietario.getId_proprietario() + " - " + "Nome: " + proprietario.getNome());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaProprietarios);
        spinner.setAdapter(adapter);

        veiculos = veiculoDAO.listar();
        listaVeiculos = new ArrayList<>();

        proprietarios = proprietarioDAO.listar();
        for (Veiculo veiculo : veiculos) {

            for (Proprietario proprietario : proprietarios) {
                if (proprietario.getId_proprietario() == veiculo.getFk_proprietario()) {
                    nome = proprietario.getNome();
                    break;
                }
            }
            listaVeiculos.add(veiculo.getId_veiculo() + " - " + "Placa: " + veiculo.getPlaca() + "\nAno: " + veiculo.getAno() + "\nMensalidade: " + veiculo.getMensalidade() +
                    "\nProprietário: " + nome);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaVeiculos);
        listView.setAdapter(adapter);

    }

}