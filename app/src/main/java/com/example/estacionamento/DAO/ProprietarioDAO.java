package com.example.estacionamento.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.estacionamento.models.DB;
import com.example.estacionamento.models.Proprietario;

import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO {
    private SQLiteDatabase db;

    public ProprietarioDAO(Context context) {
        DB dbHelper = new DB(context);
        db = dbHelper.getWritableDatabase();
    }

    public void salvar(Proprietario proprietario){
        ContentValues values = new ContentValues();

        values.put("nome", proprietario.getNome());
        values.put("cpf", proprietario.getCpf());
        values.put("email", proprietario.getEmail());
        values.put("senha", proprietario.getSenha());
        values.put("fk_vaga", proprietario.getFk_vaga());

        db.insert("proprietario", null, values);
    }

    public List<Proprietario> listar(){
        List<Proprietario> proprietarios = new ArrayList<>();
        String query = "select * from proprietario";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                Proprietario proprietario = new Proprietario();
                proprietario.setId_proprietario(cursor.getInt(0));
                proprietario.setNome(cursor.getString(1));
                proprietario.setCpf(cursor.getString(2));
                proprietario.setSenha(cursor.getString(3));
                proprietario.setEmail(cursor.getString(4));
                proprietario.setFk_vaga(cursor.getInt(5));
                proprietarios.add(proprietario);
            }while (cursor.moveToNext());
        }

        return proprietarios;
    }

    public void alterar(Proprietario proprietario){
        ContentValues values = new ContentValues();

        values.put("nome", proprietario.getNome());
        values.put("cpf", proprietario.getCpf());
        values.put("email", proprietario.getEmail());
        values.put("senha", proprietario.getSenha());
        values.put("fk_vaga", proprietario.getFk_vaga());


        db.update("proprietario", values, "id_proprietario="+proprietario.getId_proprietario(), null);
    }

    public void deletar(Proprietario proprietario){
        db.delete("proprietario", "id_proprietario=" + proprietario.getId_proprietario(), null);
    }
}
