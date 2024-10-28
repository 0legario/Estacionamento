package com.example.estacionamento.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.estacionamento.models.DB;
import com.example.estacionamento.models.Veiculo;
//import com.example.estacionamento.models.veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private SQLiteDatabase db;

    public VeiculoDAO(Context context) {
        DB dbHelper = new DB(context);
        db = dbHelper.getWritableDatabase();
    }

    public void salvar(Veiculo veiculo){
        ContentValues values = new ContentValues();

        values.put("placa", veiculo.getPlaca());
        values.put("ano", veiculo.getAno());
        values.put("mensalidade", veiculo.getMensalidade());
        values.put("fk_proprietario", veiculo.getFk_proprietario());

        db.insert("veiculo", null, values);
    }

    public List<Veiculo> listar(){
        List<Veiculo> veiculos = new ArrayList<>();
        String query = "select * from veiculo";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                Veiculo veiculo = new Veiculo();
                veiculo.setId_veiculo(cursor.getInt(0));
                veiculo.setPlaca(cursor.getString(1));
                veiculo.setMensalidade(cursor.getInt(2));
                veiculo.setAno(cursor.getInt(3));
                veiculo.setFk_proprietario(cursor.getInt(4));

                veiculos.add(veiculo);
            }while (cursor.moveToNext());
        }

        return veiculos;
    }

    public void alterar(Veiculo veiculo){
        ContentValues values = new ContentValues();

        values.put("placa", veiculo.getPlaca());
        values.put("ano", veiculo.getAno());
        values.put("mensalidade", veiculo.getMensalidade());
        values.put("fk_proprietario", veiculo.getFk_proprietario());

        db.update("veiculo", values, "id_veiculo="+veiculo.getId_veiculo(), null);
    }

    public void deletar(Veiculo veiculo){
        db.delete("veiculo", "id_veiculo=" + veiculo.getId_veiculo(), null);
    }
}
