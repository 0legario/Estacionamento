package com.example.estacionamento.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.estacionamento.models.DB;
import com.example.estacionamento.models.Vaga;

import java.util.ArrayList;
import java.util.List;

public class VagaDAO {
    private SQLiteDatabase db;

    public VagaDAO(Context context) {
        DB dbHelper = new DB(context);
        db = dbHelper.getWritableDatabase();
    }

    public void salvar(Vaga vaga){
        ContentValues values = new ContentValues();

        values.put("numero_vaga", vaga.getNumero_vaga());
        values.put("mensalidade", vaga.getMensalidade());

        db.insert("vaga", null, values);
    }

    public List<Vaga> listar(){
        List<Vaga> vagas = new ArrayList<>();
        String query = "select * from vaga";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                Vaga vaga = new Vaga();
                vaga.setId_vaga(cursor.getInt(0));
                vaga.setNumero_vaga(cursor.getInt(1));
                vaga.setMensalidade(cursor.getInt(2));
                vagas.add(vaga);
            }while (cursor.moveToNext());
        }

        return vagas;
    }

    public void alterar(Vaga vaga){
        ContentValues values = new ContentValues();

        values.put("numero_vaga", vaga.getNumero_vaga());
        values.put("mensalidade", vaga.getMensalidade());

        db.update("vaga", values, "id_vaga="+vaga.getId_vaga(), null);
    }

    public void deletar(Vaga vaga){
        db.delete("vaga", "id_vaga=" + vaga.getId_vaga(), null);
    }
}
