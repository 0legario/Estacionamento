package com.example.estacionamento.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Estacionamento";
    private static final int DATABASE_VERSION = 1;

    public DB (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableVaga = "create table vaga (" +
                "id_vaga integer primary key autoincrement," +
                "numero_vaga integer," +
                "mensalidade integer);";

        db.execSQL(createTableVaga);

        String createTableProprietario = "create table proprietario (" +
                "id_proprietario integer primary key autoincrement," +
                "nome text," +
                "cpf text unique," +
                "senha text," +
                "email text," +
                "fk_vaga integer," +
                "foreign key (fk_vaga) references vaga(id_vaga));";

        db.execSQL(createTableProprietario);

        String createTableVeiculo = "create table veiculo (" +
                "id_veiculo integer primary key autoincrement," +
                "placa text unique," +
                "mensalidade integer," +
                "ano integer," +
                "fk_proprietario integer," +
                "foreign key (fk_proprietario) references proprietario(id_proprietario));";

        db.execSQL(createTableVeiculo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists vaga");
        db.execSQL("drop table if exists proprietario");
        db.execSQL("drop table if exists veiculo");
        onCreate(db);
    }
}
