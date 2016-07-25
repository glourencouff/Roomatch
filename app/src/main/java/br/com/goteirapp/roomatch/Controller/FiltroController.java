package br.com.goteirapp.roomatch.Controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.goteirapp.roomatch.model.Filtros;
import br.com.goteirapp.roomatch.sqlite.RoomatchDBHelper;
import br.com.goteirapp.roomatch.util.RoomatchUtil;


/**
 * Created by Gabriel on 24/07/2016.
 */
public class FiltroController {

    private  SQLiteDatabase db;
    private  RoomatchDBHelper banco;

    public FiltroController (Context context){ banco = new RoomatchDBHelper(context);}


    public long insereDado(Filtros filtro){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(RoomatchUtil.FILTRO_USUARIO,filtro.getIdUsuario());
        valores.put(RoomatchUtil.FILTRO_MOBILIADO,filtro.getMobiliado());
        valores.put(RoomatchUtil.FILTRO_TIPO_VAGA,filtro.getTipoVaga());
        valores.put(RoomatchUtil.FILTRO_TIPO_RESIDENCIA,filtro.getTipoResidencia());
        valores.put(RoomatchUtil.FILTRO_VALOR_MINIMO,filtro.getMinValor());
        valores.put(RoomatchUtil.FILTRO_VALOR_MAXIMO,filtro.getMaxValor());

        resultado = db.insert(RoomatchUtil.TABELA_FILTRO,null,valores);
        db.close();

        return resultado;
    }


    public Boolean update(Filtros filtro){
        ContentValues valores = new ContentValues();
        Boolean atualizou = true;

        db = banco.getWritableDatabase();

        valores.put(RoomatchUtil.FILTRO_MOBILIADO,filtro.getMobiliado());
        valores.put(RoomatchUtil.FILTRO_TIPO_VAGA,filtro.getTipoVaga());
        valores.put(RoomatchUtil.FILTRO_TIPO_RESIDENCIA,filtro.getTipoResidencia());
        valores.put(RoomatchUtil.FILTRO_VALOR_MINIMO,filtro.getMinValor());
        valores.put(RoomatchUtil.FILTRO_VALOR_MAXIMO, filtro.getMaxValor());

        int resultado = db.update(RoomatchUtil.TABELA_FILTRO,valores,RoomatchUtil.ID_FILTRO +" = "+ filtro.getId(), null);

        if(resultado == 0){
            atualizou = false;
        }

        return atualizou;
    }


    public Filtros getFiltroByUsuario(Integer idUsuario){
        Filtros filtro = null;

        String[] colunas = new String[]{
                RoomatchUtil.ID_FILTRO,
                RoomatchUtil.FILTRO_USUARIO,
                RoomatchUtil.FILTRO_MOBILIADO,
                RoomatchUtil.FILTRO_TIPO_VAGA,
                RoomatchUtil.FILTRO_TIPO_RESIDENCIA,
                RoomatchUtil.FILTRO_VALOR_MINIMO,
                RoomatchUtil.FILTRO_VALOR_MAXIMO
        };


        Cursor cursor = db.query(RoomatchUtil.TABELA_FILTRO,
                colunas,
                RoomatchUtil.FILTRO_USUARIO+" = ?",
                new String[]{"" + idUsuario},
                null,
                null,
                null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            filtro = new Filtros();

            filtro.setId(cursor.getInt(0));
            filtro.setIdUsuario(cursor.getInt(1));
            filtro.setMobiliado(cursor.getInt(2));
            filtro.setTipoVaga(cursor.getInt(3));
            filtro.setTipoResidencia(cursor.getInt(4));
            filtro.setMinValor(cursor.getInt(5));
            filtro.setMaxValor(cursor.getInt(6));
        }

        return filtro;
    }


    public Filtros getFiltroById(Integer id){
        Filtros filtro = null;
        db = banco.getReadableDatabase();

        String[] colunas = new String[]{
                RoomatchUtil.ID_FILTRO,
                RoomatchUtil.FILTRO_USUARIO,
                RoomatchUtil.FILTRO_MOBILIADO,
                RoomatchUtil.FILTRO_TIPO_VAGA,
                RoomatchUtil.FILTRO_TIPO_RESIDENCIA,
                RoomatchUtil.FILTRO_VALOR_MINIMO,
                RoomatchUtil.FILTRO_VALOR_MAXIMO
        };


        Cursor cursor = db.query(RoomatchUtil.TABELA_FILTRO,
                colunas,
                "_id = ?",
                new String[]{""+id},
                null,
                null,
                null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            filtro = new Filtros();

            filtro.setId(cursor.getInt(0));
            filtro.setIdUsuario(cursor.getInt(1));
            filtro.setMobiliado(cursor.getInt(2));
            filtro.setTipoVaga(cursor.getInt(3));
            filtro.setTipoResidencia(cursor.getInt(4));
            filtro.setMinValor(cursor.getInt(5));
            filtro.setMaxValor(cursor.getInt(6));
        }

        return filtro;
    }
}
