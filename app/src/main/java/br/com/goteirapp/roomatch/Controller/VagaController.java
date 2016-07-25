package br.com.goteirapp.roomatch.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.goteirapp.roomatch.model.Filtros;
import br.com.goteirapp.roomatch.model.Vaga;
import br.com.goteirapp.roomatch.sqlite.RoomatchDB;
import br.com.goteirapp.roomatch.sqlite.RoomatchDBHelper;
import br.com.goteirapp.roomatch.util.RoomatchUtil;

/**
 * Created by carlos on 20/07/2016.
 */
public class VagaController {

    private SQLiteDatabase db;
    private RoomatchDBHelper banco;

    public VagaController(Context context) {
        banco = new RoomatchDBHelper(context);
    }

    public String insereDado(Vaga vaga) {
        ContentValues valores;
        long resultado;

        String retorno = "";

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(RoomatchUtil.ID_LOCADOR, vaga.getLocador());
        valores.put(RoomatchUtil.VAGA_VALOR, vaga.getVagaValor());
        valores.put(RoomatchUtil.VAGA_DESCRICAO, vaga.getVagaDescricao());
        valores.put(RoomatchUtil.VAGA_VAGA_MOBILIADA, vaga.getVagaMobiliada());
        valores.put(RoomatchUtil.VAGA_TIPO_VAGA, vaga.getVagaTipoVaga());
        valores.put(RoomatchUtil.VAGA_TIPO_RESIDENCIA, vaga.getVagaTipoMoradia());

        resultado = db.insert(RoomatchUtil.TABELA_VAGA, null, valores);
        db.close();

        if (resultado == -1) {
            retorno = "Erro ao inserir registro";

        } else {
            retorno = "Registro Inserido com sucesso";
        }


        return retorno;
    }

    public Vaga getVaga(Integer id) {
        db = banco.getReadableDatabase();
        Vaga vagaRetornada = new Vaga();

        String[] colunas = new String[]{RoomatchUtil.ID_VAGA,
                RoomatchUtil.ID_LOCADOR,
                RoomatchUtil.VAGA_VALOR,
                RoomatchUtil.VAGA_DESCRICAO,
                RoomatchUtil.VAGA_VAGA_MOBILIADA,
                RoomatchUtil.VAGA_TIPO_VAGA,
                RoomatchUtil.VAGA_TIPO_RESIDENCIA};

        Cursor cursor = db.query(RoomatchUtil.TABELA_VAGA,
                colunas,
                "_id = ?",
                new String[]{"" + id},
                null,
                null,
                null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            vagaRetornada.setId(cursor.getInt(0));
            vagaRetornada.setLocador(cursor.getInt(1));
            vagaRetornada.setVagaValor(cursor.getInt(2));
            vagaRetornada.setVagaDescricao(cursor.getString(3));
            vagaRetornada.setVagaMobiliada(cursor.getInt(4));
            vagaRetornada.setVagaTipoVaga(cursor.getInt(5));
            vagaRetornada.setVagaTipoMoradia(cursor.getInt(6));

        }


        return vagaRetornada;
    }

    public List<Vaga> getAllVagas(int idUsuario) {

        db = banco.getReadableDatabase();
        List<Vaga> retornoVagas = new ArrayList<Vaga>();

        String[] colunas = new String[]{RoomatchUtil.ID_VAGA,
                RoomatchUtil.ID_LOCADOR,
                RoomatchUtil.VAGA_VALOR,
                RoomatchUtil.VAGA_DESCRICAO,
                RoomatchUtil.VAGA_VAGA_MOBILIADA,
                RoomatchUtil.VAGA_TIPO_VAGA,
                RoomatchUtil.VAGA_TIPO_RESIDENCIA};

        Cursor cursor = db.query(RoomatchUtil.TABELA_VAGA,
                colunas,
                null,
                null,
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                if (cursor.getInt(1) != idUsuario) {
                    Vaga vagaRetornada = new Vaga();
                    vagaRetornada.setId(cursor.getInt(0));
                    vagaRetornada.setLocador(cursor.getInt(1));
                    vagaRetornada.setVagaValor(cursor.getInt(2));
                    vagaRetornada.setVagaDescricao(cursor.getString(3));
                    vagaRetornada.setVagaMobiliada(cursor.getInt(4));
                    vagaRetornada.setVagaTipoVaga(cursor.getInt(5));
                    vagaRetornada.setVagaTipoMoradia(cursor.getInt(6));
                    retornoVagas.add(vagaRetornada);
                }
            } while (cursor.moveToNext());
        }
    return retornoVagas;
    }


    public List<Vaga> getVagasByFiltros(int idUsuario,Filtros filtros) {

        db = banco.getReadableDatabase();
        List<Vaga> retornoVagas = new ArrayList<Vaga>();

        String[] colunas = new String[]{RoomatchUtil.ID_VAGA,
                RoomatchUtil.ID_LOCADOR,
                RoomatchUtil.VAGA_VALOR,
                RoomatchUtil.VAGA_DESCRICAO,
                RoomatchUtil.VAGA_VAGA_MOBILIADA,
                RoomatchUtil.VAGA_TIPO_VAGA,
                RoomatchUtil.VAGA_TIPO_RESIDENCIA};

        Cursor cursor = db.query(RoomatchUtil.TABELA_VAGA,
                colunas,
                RoomatchUtil.VAGA_VAGA_MOBILIADA+ " = ? and "+
                RoomatchUtil.VAGA_TIPO_VAGA+ " = ? and "+
                RoomatchUtil.VAGA_TIPO_RESIDENCIA+ " = ? and "+
                RoomatchUtil.VAGA_VALOR+ " >= ? and "+
                RoomatchUtil.VAGA_VALOR+ " <= ? ",
                new String[]{""+filtros.getMobiliado(),""+filtros.getTipoVaga(),""+filtros.getTipoResidencia(),""+filtros.getMinValor(),""+filtros.getMaxValor()},
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                if (cursor.getInt(1) != idUsuario) {
                    Vaga vagaRetornada = new Vaga();
                    vagaRetornada.setId(cursor.getInt(0));
                    vagaRetornada.setLocador(cursor.getInt(1));
                    vagaRetornada.setVagaValor(cursor.getInt(2));
                    vagaRetornada.setVagaDescricao(cursor.getString(3));
                    vagaRetornada.setVagaMobiliada(cursor.getInt(4));
                    vagaRetornada.setVagaTipoVaga(cursor.getInt(5));
                    vagaRetornada.setVagaTipoMoradia(cursor.getInt(6));
                    retornoVagas.add(vagaRetornada);
                }
            } while (cursor.moveToNext());
        }
        return retornoVagas;
    }
}


